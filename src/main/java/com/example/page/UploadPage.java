package com.example.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.form.upload.FileUpload;

import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.ajax.AjaxRequestTarget;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.lang.Bytes;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.Arrays;
import java.util.Date;
import java.util.Comparator;
import java.text.SimpleDateFormat;

import java.io.File;

/**
 * @author Miura Kazuto
 */
@MountPath("upload")
public class UploadPage extends WebPage {
    private final FileUploadField uploadField;
    private final File uploadDir;

    //上に表示するページ移動用のナビゲータ
    AjaxPagingNavigator navigator1;
    //下に表示するページ移動用のナビゲータ
    AjaxPagingNavigator navigator2;

    public UploadPage(){
        FeedbackPanel feedback = new FeedbackPanel("feedback");

        Form form = new Form("uploadForm");
        //ファイルのアップロードに必要な設定
        form.setMultiPart(true);
        //アップロードできるのは10キロバイトまで
        form.setMaxSize(Bytes.kilobytes(10));


        uploadField = new FileUploadField("uploadField");

        //アップロードされたファイルを保存するディレクトリを決める
        uploadDir = getDirectory("upload");

        //ファイルのアップロードを行うSubmitボタンを作成する
        Button submitButton = createSubmitButton();

        //ファイルの一覧を表示するテーブル(ListViewの親コンポーネント)
        WebMarkupContainer table = new WebMarkupContainer("table");
        //Ajaxで更新可能にする
        table.setOutputMarkupId(true);

        //アップロードされたファイルの一覧を表示するListViewを作成する
        PageableListView listView = createFileListView();

        //リストビューの上に表示するナビゲータ
        navigator1 = new AjaxPagingNavigator("navigator1", listView){
            @Override
            protected void onAjaxEvent(AjaxRequestTarget target){
                super.onAjaxEvent(target);

                //下のナビゲータを更新する
                target.add(navigator2);
            }
        };
        navigator1.setOutputMarkupId(true);

        //リストビューの下に表示するナビゲータ
        navigator2 = new AjaxPagingNavigator("navigator2", listView){
            @Override
            protected void onAjaxEvent(AjaxRequestTarget target){
                super.onAjaxEvent(target);

                //上のナビゲータを更新する
                target.add(navigator1);
            }
        };
        navigator2.setOutputMarkupId(true);

        add(feedback);

        form.add(uploadField);
        form.add(submitButton);
        add(form);

        add(navigator1);

        table.add(listView);
        add(table);

        add(navigator2);
    }

    //ファイルのアップロードを行うSubmitボタンを作成する
    private Button createSubmitButton(){
        Button submitButton = new Button("uploadButton"){
            @Override
            public void onSubmit(){
                FileUpload upload = uploadField.getFileUpload();
                if(upload == null){
                    //アップロードするファイルが見つからなければエラーメッセージ表示
                    //して後は何もしない

                    error("アップロードするファイルが見つかりません");
                    return;
                }

                //アップロードしたファイルの名前を取得
                String fileName = upload.getClientFileName();
                File uploadFile = new File(uploadDir, fileName);

                try{
                    //アップロードされたクライアントのファイルをサーバーのファイルに書き込む
                    upload.writeTo(uploadFile);
                }catch(Exception e){
                    //例外がでたら、FeedbackPanelにエラーメッセージを表示する
                    this.error(e.getMessage());
                }finally{
                    upload.closeStreams();
                }
            }
        };

        return submitButton;
    }

    //アップロードされたファイルの一覧を表示するListViewを作成する
    private PageableListView createFileListView(){

        //ファイル一覧を返すモデルを作成する
        IModel uploadListModel = new LoadableDetachableModel(){
            @Override
            protected Object load() {
                //アップロードされたファイルのリスト(一覧)を返す
                File[] fileList = uploadDir.listFiles();

                //新しいファイルが前にくるようにソートする
                Arrays.sort(fileList, new Comparator<File>(){
                    public int compare(File src, File target){
                        //更新した時間が新しい順に並べる
                        int diff = (int)(target.lastModified() - src.lastModified());

                        //更新した時間が同じ場合はファイル名でソート
                        if(diff == 0)
                            diff = src.getName().compareTo(target.getName());

                        return diff;
                    }
                });

                //Fileの配列をリストに変換して返す
                return Arrays.asList(fileList);
            }
        };

        PageableListView listView =
                new PageableListView("uploadList", uploadListModel, 5){

                    @Override
                    protected void populateItem(ListItem listItem){
                        File file = (File)listItem.getModelObject();

                        //ファイルダウンロード用のリンクを作成
                        DownloadLink link = new DownloadLink("downloadLink", file);
                        link.add(new Label("fileName", file.getName()));
                        listItem.add(link);

                        //ファイルサイズを表示する
                        listItem.add(new Label("fileSize", file.length() +"B"));

                        //日付を表示する
                        Date date = new Date(file.lastModified());
                        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                        listItem.add(new Label("uploadTime", format.format(date)));
                    }
                };

        return listView;
    }

    //コンテキストパス上にあるdirNameのディレクトリの絶対パスを求める
    private File getDirectory(String dirName){
        WebApplication application = (WebApplication)getApplication();
        String uploadPath = application.getServletContext().getRealPath(dirName);
        File dir = new File(uploadPath);

        //ディレクトリが存在しなければ作成する
        if(!dir.exists())
            dir.mkdir();

        return dir;
    }
}
