package com.sec421.controller.ui;
/**
 * @author 0x421
 * @date 2023/12/28 11:20
 * @github https://github.com/0x7eTeam
 */
import com.sec421.controller.baseTools.*;
import com.sec421.controller.dbTools.MySQLController;
import com.sec421.controller.dbTools.OracleController;
import com.sec421.controller.informationCollection.FofaController;
import com.sec421.controller.informationCollection.Quake360Controller;
import com.sec421.controller.informationCollection.YingTuController;
import com.sec421.controller.js.JsController;
import com.sec421.controller.js.NetworkTask;
import com.sec421.controller.linkTools.LinkToolsController;
import com.sec421.controller.postPentest.ExecController;
import com.sec421.controller.postPentest.InteractionExecController;
import com.sec421.controller.vulnCheck.BatchVulCheckTask;
import com.sec421.controller.vulnCheck.PocTools;
import com.sec421.core.Constants;
import com.sec421.tools.IniFileEntity;
import com.sec421.tools.Tools;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.concurrent.Task;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.apache.log4j.Logger;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// 主页面相关逻辑
public class MainController  {
    public static  Logger logger = Logger.getLogger(MainController.class);
    @FXML
    private MenuItem proxySetupBtn;
    @FXML
    private MenuItem api_setting;
    @FXML
    private TextField aaaTextField,aabInput1,aabInput2,aabInput3,aabInput4,aabInput5,aabInput6,aabInput7,aabInput8,
            aabInput9,aabInput10,aabInput11,aabInput12,aabInput13,aabInput14,aabip,aabport,aabsrcfile,
            aabdstfile,acTextField1,acTextField2,afTextField,baTextField,bbTextField1,bbTextField2,bcTextField,
            execIPTextField,execNAMETextField,execPASSTextField,execCMDTextField,execCMDTextField1,MySQLIPTextField,
            MySQLPORTETextField,MySQLUSERTextField,MySQLPASSTextField,MySQLSQLTextField,OracleIPTextField,
            OraclePORTETextField,OracleUSERTextField,OraclePASSTextField,OracleSQLTextField,OracleServerTextField,
            jsURLTextField,jsCTXTextField;
    @FXML
    public TextArea aaaTextArea,abTextArea1,abTextArea2,acTextArea1,acTextArea2,acTextArea3,acTextArea4,
            acTextArea5, acTextArea6, acTextArea7, acTextArea8, acTextArea9, acTextArea10, acTextArea11,
            acTextArea12, acTextArea13,aeTextArea1,aeTextArea2,afTextArea1,afTextArea2,afTextArea3,
            afTextArea4,baTextArea,bbTextArea,bcTextArea,execTextArea,MysqlTextArea,OracleTextArea,
            jsInterFaceTextArea,jsResultTextArea;
    @FXML
    private RadioButton wmiRadioBtn,psRadioBtn,smbRadioBtn,atRadioBtn,dcomRadioBtn,getRadioBtn,postRadioBtn;
    @FXML
    private ToggleGroup execType,jsReqType;
    @FXML
    private WebView adWebView,auxwebsite;
    @FXML
    private Button caBtn2,caBtn3,caBtn4,sendAttackBtn,exitSendBtn;
    @FXML
    private TableView<BatchVulCheckTask> catableView;
    @FXML
    private TableColumn<?, ?> catableColumnResult,catableColumnUrl,catableColumnIndex;
    @FXML
    public ComboBox<String> abComboBox,bbComboBox1,bbComboBox2,bcComboBox,caComboBox,execAuthComboBox,
            execCharSetComboBox,MySQLComboBox,MySQLComboBoxTable,OracleComboBoxTable;

    private List<String> urlList;
    private String save_path,exec_type,jsreq_type,exec_auth,exec_charset,db_ip,db_port,db_user,db_password,db_name,db_table,db_server;
    private InteractionExecController interactionExecController;
    private int batch_job_count;
    @FXML
    public static Label proxyStatusLabel = new Label();



    public static Map<String, Object> history = new HashMap<String, Object>();

    // 设置相关信息保存
    public static Map<String, Object> settingInfo = new HashMap();

    // 监听菜单关于事件
    @FXML
    public void about() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        // 点 x 退出
        Window window = alert.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest((e) -> {
            window.hide();
        });

        DialogPane dialogPane = new DialogPane();

        TextArea textArea = new TextArea(Constants.SECURITYSTATEMENT);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setPrefHeight(220);

        dialogPane.setContent(textArea);

        Image image = new Image((getClass().getResource("/img/logo_2256.gif")).toString());
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        imageView.setFitWidth(220);
        imageView.setPreserveRatio(true);

        dialogPane.setGraphic(imageView);

        ButtonType confirm = new ButtonType("确认");
        dialogPane.getButtonTypes().setAll(confirm);
        alert.setDialogPane(dialogPane);

        alert.showAndWait();

    }
    // 监听菜单更新事件
    @FXML
    public void update() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        // 点 x 退出
        Window window = alert.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest((e) -> {
            window.hide();
        });

        DialogPane dialogPane = new DialogPane();

        TextArea textArea = new TextArea(Constants.UPDATELOG);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setPrefHeight(220);

        dialogPane.setContent(textArea);

        Image image = new Image((getClass().getResource("/img/logo_2256.gif")).toString());
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        imageView.setFitWidth(220);
        imageView.setPreserveRatio(true);

        dialogPane.setGraphic(imageView);

        ButtonType confirm = new ButtonType("确认");
        dialogPane.getButtonTypes().setAll(confirm);
        alert.setDialogPane(dialogPane);

        alert.showAndWait();

    }
    // 监听菜单事件
    private void initToolbar() {
        //代理 设置
        this.proxySetupBtn.setOnAction((event) -> {
            Alert inputDialog = new Alert(Alert.AlertType.NONE);
            Window window = inputDialog.getDialogPane().getScene().getWindow();
            window.setOnCloseRequest((e) -> {
                window.hide();
            });
            inputDialog.setTitle("代理设置");
            ToggleGroup statusGroup = new ToggleGroup();
            RadioButton enableRadio = new RadioButton("启用");
            RadioButton disableRadio = new RadioButton("禁用");
            enableRadio.setToggleGroup(statusGroup);
            disableRadio.setToggleGroup(statusGroup);
            disableRadio.setSelected(true);
            HBox statusHbox = new HBox();
            statusHbox.setSpacing(10.0D);
            statusHbox.getChildren().add(enableRadio);
            statusHbox.getChildren().add(disableRadio);
            GridPane proxyGridPane = new GridPane();
            proxyGridPane.setVgap(15.0D);
            proxyGridPane.setPadding(new Insets(20.0D, 20.0D, 0.0D, 10.0D));
            Label typeLabel = new Label("类型：");
            ComboBox typeCombo = new ComboBox();
            typeCombo.setItems(FXCollections.observableArrayList(new String[]{"HTTP", "SOCKS"}));
            typeCombo.getSelectionModel().select(0);
            Label IPLabel = new Label("IP地址：");
            TextField IPText = new TextField("127.0.0.1");
            Label PortLabel = new Label("端口：");
            TextField PortText = new TextField("8080");
            Label userNameLabel = new Label("用户名：");
            TextField userNameText = new TextField();
            Label passwordLabel = new Label("密码：");
            TextField passwordText = new TextField();
            Button cancelBtn = new Button("取消");
            Button saveBtn = new Button("保存");


            try {
                Proxy proxy = (Proxy)settingInfo.get("proxy");
                if (proxy != null) {
                    enableRadio.setSelected(true);

                } else {
                    disableRadio.setSelected(true);
                }

                if(settingInfo.size() > 0) {
                    String type = (String)settingInfo.get("type");
                    if (type.equals("HTTP")) {
                        typeCombo.getSelectionModel().select(0);
                    } else if (type.equals("SOCKS")) {
                        typeCombo.getSelectionModel().select(1);
                    }

                    String ip = (String)settingInfo.get("ip");
                    String port = (String)settingInfo.get("port");
                    IPText.setText(ip);
                    PortText.setText(port);
                    String username = (String)settingInfo.get("username");
                    String password = (String)settingInfo.get("password");
                    userNameText.setText(username);
                    passwordText.setText(password);
                }


            } catch (Exception var) {
                proxyStatusLabel.setText("代理服务器配置加载失败。");
                logger.debug(var);
            }


            saveBtn.setOnAction((e) -> {
                if (disableRadio.isSelected()) {
                    settingInfo.put("proxy", (Object)null);
                    proxyStatusLabel.setText("");
                    inputDialog.getDialogPane().getScene().getWindow().hide();
                } else {

                    final String type;
                    if (!userNameText.getText().trim().equals("")) {
                        final String proxyUser = userNameText.getText().trim();
                        type = passwordText.getText();
                        Authenticator.setDefault(new Authenticator() {
                            public PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(proxyUser, type.toCharArray());
                            }
                        });
                    } else {
                        Authenticator.setDefault((Authenticator)null);
                    }

                    settingInfo.put("username", userNameText.getText());
                    settingInfo.put("password", passwordText.getText());
                    InetSocketAddress proxyAddr = new InetSocketAddress(IPText.getText(), Integer.parseInt(PortText.getText()));

                    settingInfo.put("ip", IPText.getText());
                    settingInfo.put("port", PortText.getText());
                    String proxy_type = typeCombo.getValue().toString();
                    settingInfo.put("type", proxy_type);
                    Proxy proxy;
                    if (proxy_type.equals("HTTP")) {
                        proxy = new Proxy(Proxy.Type.HTTP, proxyAddr);
                        settingInfo.put("proxy", proxy);
                    } else if (proxy_type.equals("SOCKS")) {
                        proxy = new Proxy(Proxy.Type.SOCKS, proxyAddr);
                        settingInfo.put("proxy", proxy);
                    }

                    proxyStatusLabel.setText("代理生效中");
                    inputDialog.getDialogPane().getScene().getWindow().hide();
                }
            });

            cancelBtn.setOnAction((e) -> {
                inputDialog.getDialogPane().getScene().getWindow().hide();
            });
            proxyGridPane.add(statusHbox, 1, 0);
            proxyGridPane.add(typeLabel, 0, 1);
            proxyGridPane.add(typeCombo, 1, 1);
            proxyGridPane.add(IPLabel, 0, 2);
            proxyGridPane.add(IPText, 1, 2);
            proxyGridPane.add(PortLabel, 0, 3);
            proxyGridPane.add(PortText, 1, 3);
            proxyGridPane.add(userNameLabel, 0, 4);
            proxyGridPane.add(userNameText, 1, 4);
            proxyGridPane.add(passwordLabel, 0, 5);
            proxyGridPane.add(passwordText, 1, 5);
            HBox buttonBox = new HBox();
            buttonBox.setSpacing(20.0D);
            buttonBox.setAlignment(Pos.CENTER);
            buttonBox.getChildren().add(cancelBtn);
            buttonBox.getChildren().add(saveBtn);
            GridPane.setColumnSpan(buttonBox, 2);
            proxyGridPane.add(buttonBox, 0, 6);
            inputDialog.getDialogPane().setContent(proxyGridPane);
            inputDialog.showAndWait();
        });
        //API 设置
        this.api_setting.setOnAction((event) -> {
            Alert inputDialog = new Alert(Alert.AlertType.NONE);
            Window window = inputDialog.getDialogPane().getScene().getWindow();
            window.setOnCloseRequest((e) -> {
                window.hide();
            });
            inputDialog.setTitle("配置设置");
            HBox statusHbox = new HBox();
            statusHbox.setSpacing(10.0D);

            GridPane proxyGridPane = new GridPane();
            proxyGridPane.setVgap(15.0D);
            proxyGridPane.setPadding(new Insets(20.0D, 20.0D, 0.0D, 10.0D));
            Label fofaEmailLabel = new Label("fofa_email：");
            TextField fofaEmailText = new TextField();
            Label fofaKeyLabel = new Label("fofa_key：");
            TextField fofaKeyText = new TextField();

            Label hunterKeyLabel = new Label("hunter_key：");
            TextField hunterKeyText = new TextField();
            Label quakeKeyLabel = new Label("quake_key：");
            TextField quakeKeyText = new TextField();


            Label pythonPATHLabel = new Label("py_path：");
            TextField pythonPATHText = new TextField();

            Button cancelBtn = new Button("取消");
            Button saveBtn = new Button("保存");
            File file = new File(Constants.CONFPATH);
            try {
                if (file.exists()) {
                    String fofaAPI = Tools.getProperty("fofa");
                    String hunterApiKey = Tools.getProperty("hunter");
                    String quakeApiKey = Tools.getProperty("quake");
                    String pythonPATH = Tools.getProperty("py_path");
                    hunterKeyText.setText(hunterApiKey);
                    quakeKeyText.setText(quakeApiKey);
                    pythonPATHText.setText(pythonPATH);
                    this.settingInfo.put("hunter_key", hunterApiKey);
                    this.settingInfo.put("quake_key", quakeApiKey);
                    this.settingInfo.put("py_path", pythonPATH);
                    String[] EmaliKey = fofaAPI.split(":");
                    if(EmaliKey.length == 2) {
                        String email = EmaliKey[0];
                        String key = EmaliKey[1];
                        fofaEmailText.setText(email);
                        fofaKeyText.setText(key);
                        this.settingInfo.put("fofa_email", email);
                        this.settingInfo.put("fofa_key", key);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("提示");
                        alert.setHeaderText(null);
                        alert.setContentText("配置错误\n");

                        alert.showAndWait();
                    }
                }


            } catch (Exception var) {
                this.proxyStatusLabel.setText("配置加载失败。");
                logger.error(var.getStackTrace());
            }


            saveBtn.setOnAction((e) -> {
                this.settingInfo.put("fofa_email", fofaEmailText.getText());
                this.settingInfo.put("fofa_key", fofaKeyText.getText());
                this.settingInfo.put("hunter_key", hunterKeyText.getText());
                this.settingInfo.put("quake_key", quakeKeyText.getText());
                this.settingInfo.put("py_path", pythonPATHText.getText());
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    List<IniFileEntity> list = Arrays.asList(
                            new IniFileEntity("fofa","email",fofaEmailText.getText()),
                            new IniFileEntity("fofa","apikey",fofaKeyText.getText()),
                            new IniFileEntity("hunter","apikey",hunterKeyText.getText()),
                            new IniFileEntity("quake","apikey",quakeKeyText.getText()),
                            new IniFileEntity("python","path",pythonPATHText.getText()));
                    Tools.creatIniFile(Constants.CONFPATH,list);
                    bbTextField1.setText(hunterKeyText.getText());

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("提示");
                    alert.setHeaderText(null);
                    alert.setContentText("配置保存成功\n");
                    alert.showAndWait();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                proxyStatusLabel.setText("配置已保存");
                inputDialog.getDialogPane().getScene().getWindow().hide();

            });
            cancelBtn.setOnAction((e) -> {
                inputDialog.getDialogPane().getScene().getWindow().hide();
            });
            proxyGridPane.add(statusHbox, 1, 0);
            proxyGridPane.add(fofaEmailLabel, 0, 2);
            proxyGridPane.add(fofaEmailText, 1, 2);
            proxyGridPane.add(fofaKeyLabel, 0, 3);
            proxyGridPane.add(fofaKeyText, 1, 3);
            proxyGridPane.add(hunterKeyLabel,0,4);
            proxyGridPane.add(hunterKeyText,1,4);
            proxyGridPane.add(quakeKeyLabel,0,5);
            proxyGridPane.add(quakeKeyText,1,5);
            proxyGridPane.add(pythonPATHLabel,0,6);
            proxyGridPane.add(pythonPATHText,1,6);


            HBox buttonBox = new HBox();
            buttonBox.setAlignment(Pos.CENTER);
            buttonBox.setSpacing(30.0D);
            buttonBox.getChildren().add(cancelBtn);
            buttonBox.getChildren().add(saveBtn);
            GridPane.setColumnSpan(buttonBox, 2);
            proxyGridPane.add(buttonBox, 0, 8);
            inputDialog.getDialogPane().setContent(proxyGridPane);
            inputDialog.showAndWait();
        });
    }
    // 加载
    @FXML
    public void initialize() {
        // 设置
        this.initToolbar();
        this.initabComboBox();
        this.initbbComboBox();
        this.initDictory();
        this.initYingTu();
        this.initTableColumn();
        this.initExecComboBox();
    }
    private void initabComboBox() {
        ObservableList<String> encodeMethods = FXCollections.observableArrayList(new String[] { "base64", "unicode","url","字符串反转","ASCII","rot13","摩斯密码" });
        this.abComboBox.setItems(encodeMethods);
    }
    private void initbbComboBox() {
        ObservableList<String> encodeMethods1 = FXCollections.observableArrayList(new String[] { "1", "2","3","4","5","6","7","8","9","10" });
        this.bbComboBox1.setItems(encodeMethods1);
        this.bcComboBox.setItems(encodeMethods1);
        ObservableList<String> encodeMethods2 = FXCollections.observableArrayList(new String[] { "是","否" });
        this.bbComboBox2.setItems(encodeMethods2);
        ObservableList<String> poclist = FXCollections.observableArrayList(PocTools.getpyFiles());
        this.caComboBox.setItems(poclist);
    }
    private void initExecComboBox(){
        ObservableList<String> authType = FXCollections.observableArrayList(new String[] { "密码", "哈希"});
        this.execAuthComboBox.setItems(authType);
        ObservableList<String> charsetType = FXCollections.observableArrayList(new String[] { "GBK", "utf-8"});
        this.execCharSetComboBox.setItems(charsetType);
    }
    private void initTableColumn(){
        this.catableColumnIndex.setCellValueFactory(new PropertyValueFactory("index"));
        this.catableColumnUrl.setCellValueFactory(new PropertyValueFactory<>("url"));
        this.catableColumnResult.setCellValueFactory(new PropertyValueFactory("result"));
//        this.catableView.setRowFactory( tv -> {
//            TableRow<BatchVulCheckTask> row = new TableRow<>();
//            row.setOnMouseClicked(event -> {
//                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
//                    BatchVulCheckTask rowData = row.getItem();
//                    row.setEditable(true);
//                    System.out.println(rowData.getUrl());
//                }
//            });
//            return row ;
//        });
        ContextMenu contextMenu = new ContextMenu();
        MenuItem copyMenuItem = new MenuItem("复制");
        copyMenuItem.setOnAction(event -> {
            StringBuilder clipboardString = new StringBuilder();
            // 遍历选中的行并复制每个单元格的内容
            for (BatchVulCheckTask item : this.catableView.getSelectionModel().getSelectedItems()) {
//                for (TableColumn<BatchVulCheckTask, ?> column : this.catableView.getColumns()) {
//                    Object cellValue = column.getCellData(item);
//                    clipboardString.append(cellValue == null ? "" : cellValue.toString()).append("\t");
//                }
//                clipboardString.append("\n");
                //修改为只复制url
                clipboardString.append(item.getUrl().trim());

            }

            // 将复制的内容添加到剪贴板
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putString(clipboardString.toString());
            Clipboard.getSystemClipboard().setContent(clipboardContent);
        });
        contextMenu.getItems().add(copyMenuItem);
        this.catableView.setContextMenu(contextMenu);
        this.catableView.setOnContextMenuRequested(event -> contextMenu.show(this.catableView, event.getScreenX(), event.getScreenY()));
    }
    private void initYingTu() {
        try {
            String hunterApiKey = Tools.getProperty("hunter");
            bbTextField1.setText(hunterApiKey);
        } catch (IOException e) {
            bbTextField1.setText("请输入hunter的apikey");
        }
    }

    private void initDictory(){
        DictionaryController dictionaryController = new DictionaryController();
        String html = dictionaryController.getHtmlContent();
        adWebView.getEngine().loadContent(html);
        adWebView.getEngine().setJavaScriptEnabled(true);
    }

    @FXML
    private void ChoiceExecType(ActionEvent actionEvent){
        if (execType.getSelectedToggle().equals(wmiRadioBtn)){
            this.exec_type = "wmiexec";
        }else if (execType.getSelectedToggle().equals(psRadioBtn)){
            this.exec_type = "psexec";
        }else if (execType.getSelectedToggle().equals(smbRadioBtn)){
            this.exec_type = "smbexec";
        }else if (execType.getSelectedToggle().equals(atRadioBtn)){
            this.exec_type = "atexec";
        }else if (execType.getSelectedToggle().equals(dcomRadioBtn)){
            this.exec_type = "dcomexec";
        }
        this.execTextArea.setText("您选择的方式是: "+this.exec_type);
    }


    public void jsGetInterFaceBtn(ActionEvent actionEvent) throws Exception {
        String url = this.jsURLTextField.getText();

        JsController jsController = new JsController();
        if (url.endsWith(".js") || url.contains(".js?")){
            String jsinterface = jsController.getJSInterFaceResult(url);
            if (jsinterface==""){
                jsinterface = "未找到js接口";
            }
            this.jsInterFaceTextArea.setText(jsinterface);
            Tools.alert("提示","处理完毕");
        }else {
            String jsurlResult = jsController.getJSURLResult(url);
            if (jsurlResult==""){
                jsurlResult = "未找到jsURL";
            }
            this.jsInterFaceTextArea.setText(jsurlResult);
            Tools.alert("提示","处理完毕");
        }
    }

    public void jsAddCTXBtn(ActionEvent actionEvent){
        String interface_str = this.jsInterFaceTextArea.getText();
        String ctx = this.jsCTXTextField.getText();
        JsController jsController = new JsController();
        String result = jsController.getCTXResult(interface_str,ctx);
        this.jsInterFaceTextArea.clear();
        this.jsInterFaceTextArea.setText(result);
    }
    public void jsGetStatusBtn(ActionEvent actionEvent) throws MalformedURLException {
        JsController jsController = new JsController();
        String jsurl = this.jsURLTextField.getText();
        String reqMethod = this.jsreq_type;
        String interface_ulr = this.jsInterFaceTextArea.getText();
        if (jsurl == null || reqMethod == null || interface_ulr == null) {
            Tools.alert("错误", "请选择好配置");
        } else if (jsurl.isEmpty() || reqMethod.isEmpty() || interface_ulr.isEmpty()) {
            Tools.alert("错误", "请选择好配置");
        } else {
            List<String> interfaces = Arrays.asList(interface_ulr.split("\n"));
            String domain = Tools.extractDomain(jsurl);
            for (String interface_url : interfaces) {
                String url = null;
                if (interface_url.startsWith("/")) {
                    try {
                        url = Tools.urlPretreatment(domain + interface_url);
                        url = Tools.normalizedURI(url);
                    } catch (URISyntaxException e) {
                        continue;
                    }
                }else {
                    URL myurl = new URL(jsurl);
                    String mypath = myurl.getPath();
                    String fileName = mypath.substring(mypath.lastIndexOf('/') + 1);
                    domain = jsurl.replace(fileName,"");
                    url = domain + interface_url;
                    try {
                        url = Tools.normalizedURI(url);
                    } catch (URISyntaxException e) {
                        continue;
                    }
                }

                NetworkTask task = new NetworkTask(url, reqMethod);
                task.valueProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        Platform.runLater(() -> jsResultTextArea.appendText(newValue));
                    }
                });

                new Thread(task).start();
            }
        }
    }

    @FXML
    private void ChoiceJSReqMethodType(ActionEvent actionEvent){
        if (jsReqType.getSelectedToggle().equals(getRadioBtn)){
            this.jsreq_type = "get";
        }else if (jsReqType.getSelectedToggle().equals(postRadioBtn)){
            this.jsreq_type = "post";
        }
    }

    public void MySQLtestBtn(ActionEvent actionEvent) throws SQLException {
        this.db_ip = MySQLIPTextField.getText();
        this.db_port = MySQLPORTETextField.getText();
        this.db_user = MySQLUSERTextField.getText();
        this.db_password = MySQLPASSTextField.getText();
        MySQLController mySQLController = new MySQLController();
        boolean connect = mySQLController.connect(this.db_ip,this.db_port,this.db_user,this.db_password);
        if (connect){
            this.MySQLComboBox.setDisable(false);
            this.MysqlTextArea.setText("数据库连接成功,请选择database");
            String[] dbnames = mySQLController.getDBNAME(this.db_ip, this.db_port,this.db_user, this.db_password);
            ObservableList<String> db_list = FXCollections.observableArrayList(dbnames);
            this.MySQLComboBox.setItems(db_list);
        }else {
            this.MySQLComboBox.setDisable(true);
            this.MysqlTextArea.setText("数据库连接失败,请检测网络以及数据库信息");
        }
    }
    public void MySQLComboBox(ActionEvent actionEvent){
        this.db_name = this.MySQLComboBox.getValue();
        MySQLController mySQLController = new MySQLController();
        String[] tbaleNames = mySQLController.getTbaleName(this.db_ip, this.db_port, this.db_user, this.db_password, this.db_name);
        ObservableList<String> tbaleName_list = FXCollections.observableArrayList(tbaleNames);
        this.MySQLComboBoxTable.setItems(tbaleName_list);
        this.MySQLComboBoxTable.setDisable(false);
    }
    public void MySQLComboBoxTable(ActionEvent actionEvent){
        this.db_table = this.MySQLComboBoxTable.getValue();
        MySQLController mySQLController = new MySQLController();
        String tableData = mySQLController.getTableData(this.db_ip, this.db_port, this.db_user, this.db_password, this.db_name, this.db_table);
        this.MysqlTextArea.setText(tableData);
    }
    public void MySQLEXECBtn(ActionEvent actionEvent){
        String sql = this.MySQLSQLTextField.getText();
        MySQLController mySQLController = new MySQLController();
        String s = mySQLController.executeSQL(this.db_ip, this.db_port, this.db_user, this.db_password, this.db_name, sql);
        this.MysqlTextArea.setText(s);
    }


    public void OracletestBtn(ActionEvent actionEvent) throws SQLException {
        this.db_ip = OracleIPTextField.getText();
        this.db_port = OraclePORTETextField.getText();
        this.db_user = OracleUSERTextField.getText();
        this.db_password = OraclePASSTextField.getText();
        this.db_server = OracleServerTextField.getText();
        OracleController oracleController = new OracleController();
        boolean connect = oracleController.connect(this.db_ip,this.db_port,this.db_user,this.db_password,this.db_server);
        if (connect){
            this.OracleComboBoxTable.setDisable(false);
            this.OracleTextArea.setText("数据库连接成功,请选择数据表");
            String[] dbnames = oracleController.getTbaleName(this.db_ip, this.db_port,this.db_user, this.db_password,this.db_server);
            ObservableList<String> db_list = FXCollections.observableArrayList(dbnames);
            this.OracleComboBoxTable.setItems(db_list);
        }else {
            this.OracleComboBoxTable.setDisable(true);
            this.OracleTextArea.setText("数据库连接失败,请检测网络以及数据库信息");
        }
    }


    public void OracleEXECBtn(ActionEvent actionEvent){
        String sql = this.OracleSQLTextField.getText();
        OracleController oracleController = new OracleController();
        String s = oracleController.executeSQL(this.db_ip, this.db_port, this.db_user, this.db_password, this.db_server, sql);
        this.OracleTextArea.setText(s);
    }

    public void OracleComboBoxTable(ActionEvent actionEvent){
        this.db_table = this.OracleComboBoxTable.getValue();
        OracleController oracleController = new OracleController();
        String tableData = oracleController.getTableData(this.db_ip, this.db_port, this.db_user, this.db_password, this.db_server, this.db_table);
        this.OracleTextArea.setText(tableData);
    }

    public void execAttack(ActionEvent actionEvent){
        String execIP = execIPTextField.getText();
        String execNAME = execNAMETextField.getText();
        String execPASS = execPASSTextField.getText();
        String execCMD = execCMDTextField.getText();
        String execAUTH = execAuthComboBox.getValue();
        String execCHARSET = execCharSetComboBox.getValue();
        if (execIP==null||execNAME==null||execPASS==null||execCMD==null||execAUTH==null||execCHARSET==null||this.exec_type==null){
            Tools.alert("错误","请选择好配置");
        } else if (execIP.isEmpty()||execNAME.isEmpty()||execPASS.isEmpty()||execCMD.isEmpty()||execAUTH.isEmpty()||execCHARSET.isEmpty()||this.exec_type.isEmpty()) {
            Tools.alert("错误","请选择好配置");
        }else{
            String result = "";
            if(this.exec_type == "smbexec" || this.exec_type == "atexec"){
                this.execCMDTextField1.setDisable(false);
                this.sendAttackBtn.setDisable(false);
                this.exitSendBtn.setDisable(false);
                this.interactionExecController = new InteractionExecController();
                this.interactionExecController.exec(this.execTextArea,this.exec_type, execIP, execNAME, execPASS, execAUTH, execCHARSET);
            }else{
                ExecController execController = new ExecController();
                result = execController.exec(this.exec_type, execIP, execNAME, execPASS, execCMD, execAUTH, execCHARSET);
            }
            this.execTextArea.setText(result);
        }

    }
    public void sendAttack(ActionEvent actionEvent){
        String shell_cmd = execCMDTextField1.getText();
        this.interactionExecController.sendCommand(shell_cmd);
    }

    public void exitSendBtn(ActionEvent actionEvent){
        this.interactionExecController.stopSendCommand();
        this.execCMDTextField1.setDisable(true);
        this.sendAttackBtn.setDisable(true);
        this.exitSendBtn.setDisable(true);
    }
    public void setProxyStatusLabel(String value) {
        this.proxyStatusLabel.setText(value);
    }

    public void aaaBtn(ActionEvent actionEvent) {
        String ip = aaaTextField.getText();
        BaseToolsController baseToolsController = new BaseToolsController();
        aaaTextArea.setText(baseToolsController.BruteforceTools(ip));
    }
    public void baBtn(ActionEvent actionEvent) throws Exception {
        String searchText = baTextField.getText();
        FofaController fofaController = new FofaController();
        String searchResult = fofaController.fofaSearch(searchText);
        baTextArea.setText(searchResult);
    }
    public void bbBtn(ActionEvent actionEvent) throws Exception{
        String apikey = bbTextField1.getText();
        String searchText = bbTextField2.getText();
        String page = bbComboBox1.getValue();
        String is_web = bbComboBox2.getValue();
        if (page == null || is_web == null){
            bbTextArea.setText("请选择 页码 和 是否web资产");
        }else {
            YingTuController yingTuController = new YingTuController();
            String searchResult = yingTuController.yintuSearch(searchText,apikey,page,is_web);
            bbTextArea.setText(searchResult);
        }
    }
    public void bcBtn(ActionEvent actionEvent) throws IOException {
        String queryString = bcTextField.getText();
        String page = bcComboBox.getValue();
        if (page == null){
            bcTextArea.setText("请选择 页码");
        }
        Quake360Controller quake360Controller = new Quake360Controller();
        bcTextArea.setText(quake360Controller.quake360Search(queryString,Integer.parseInt(page)));
    }

    public void setTableView(List<String> aList,TableView tableView){
        ObservableList<BatchVulCheckTask> list = FXCollections.observableArrayList();
        for(int i = 0; i<aList.size();i++){
            BatchVulCheckTask batchCheckTask = new BatchVulCheckTask(aList.get(i), i + 1);
            list.add(batchCheckTask);
        }
        this.catableView.setItems(list);

    }
    public void caBtn2(ActionEvent actionEvent){

        String results = null;
        if (this.catableView.getItems().size() == 0){
            Tools.alert("提示","没有可以保存的数据");
        }

        String batch_filePath = this.save_path.trim();
        StringBuilder saveNameSB = new StringBuilder();
        if (!batch_filePath.equals("")) {
            File f = new File(batch_filePath);
            saveNameSB.append(f.getName(), 0, f.getName().lastIndexOf("."));
        }
        saveNameSB.append("_");
        saveNameSB.append(Tools.getFDate());
        saveNameSB.append(".csv");
        String path = saveNameSB.toString();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.catableView.getItems().size(); i++) {
            BatchVulCheckTask batchCheckTask = this.catableView.getItems().get(i);
            sb.append(batchCheckTask.getIndex());
            sb.append(",");
            sb.append(batchCheckTask.getUrl());
            sb.append(",");
            if (batchCheckTask.getResult() == null) {
                sb.append("暂未检测");
            } else {
                sb.append(batchCheckTask.getResult());
            }
            sb.append("\r\n");
        }
        results = Tools.writeResult(path, sb.toString(), true);
        Tools.alert("提示",results);
    }
    public void caBtn4(ActionEvent actionEvent){
        this.caBtn3.setDisable(false);
        this.caBtn2.setDisable(false);
        this.caBtn4.setDisable(true);
        this.catableView.getItems().forEach(Task::cancel);
    }
    public void caBtn3(ActionEvent actionEvent) throws IOException {
        int tableSize = this.catableView.getItems().size();
        String pyScriptFile = caComboBox.getValue();
        if (pyScriptFile == null){
            Tools.alertINFO("请选择您需要使用的py脚本");
        }else if (Tools.getProperty("py_path")== null || Tools.getProperty("py_path")==""){
            Tools.alertINFO("请先配置python的路径");
        }else if (tableSize>0){
            if (this.urlList.size()>0){
                this.catableView.getItems().clear();
                Executor exec = Executors.newFixedThreadPool(10, r -> {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                });
                for (int i = 0; i < this.urlList.size(); i++) {
                    this.caBtn4.setDisable(false);
                    String url = this.urlList.get(i);
                    BatchVulCheckTask batchVulCheckTask = new BatchVulCheckTask(url, i + 1, pyScriptFile);
                    exec.execute((Runnable)batchVulCheckTask);
                    this.catableView.getItems().addAll(new BatchVulCheckTask[] { batchVulCheckTask });
                    batchVulCheckTask.setOnSucceeded(event1 -> {
                        this.batch_job_count++;
                        if (this.batch_job_count == this.urlList.size())
                            Tools.alertINFO("漏洞检测完毕");
                    });
                    batchVulCheckTask.setOnCancelled(event12 -> {
                        this.batch_job_count++;
                        if (this.batch_job_count == this.urlList.size())
                            Tools.alertINFO("漏洞检测完毕");
                    });
                    batchVulCheckTask.setOnFailed(event13 -> {
                        this.batch_job_count++;
                        if (this.batch_job_count == this.urlList.size())
                            Tools.alertINFO("漏洞检测完毕");
                    });
                }
            }else{
                Tools.alertINFO("请先导入待检测的目标");
            }
        }else{
            Tools.alertINFO("请先导入待检测的目标");
        }
    }
    public void baPOCBtn(ActionEvent actionEvent) {
        String url = baTextArea.getText();
        this.urlList = Tools.convertToArrayListMotd(url);
        this.setTableView(this.urlList,this.catableView);
        this.caBtn2.setDisable(false);
        this.caBtn3.setDisable(false);
    }
    public void bbPOCBtn(ActionEvent actionEvent) {
        String url = bbTextArea.getText();
        this.urlList = Tools.convertToArrayListMotd(url);
        this.setTableView(this.urlList,this.catableView);
        this.caBtn2.setDisable(false);
        this.caBtn3.setDisable(false);
    }
    public void bcPOCBtn(ActionEvent actionEvent) {
        String url = bcTextArea.getText();
        this.urlList = Tools.convertToArrayListMotd(url);
        this.setTableView(this.urlList,this.catableView);
        this.caBtn2.setDisable(false);
        this.caBtn3.setDisable(false);
    }
    public void baEXPBtn(ActionEvent actionEvent) {
        String ip = aaaTextField.getText();
        BaseToolsController baseToolsController = new BaseToolsController();
        aaaTextArea.setText(baseToolsController.BruteforceTools(ip));
    }
    public void afBtn(ActionEvent actionEvent) {
        DNSLogerController dnsLogerController = new DNSLogerController();
        String DNSURL = this.afTextField.getText();
        dnsLogerController.RCEGenerate(afTextArea1,DNSURL);
        dnsLogerController.SQLGenerate(afTextArea2,DNSURL);
        dnsLogerController.XXEGenerate(afTextArea3,DNSURL);
        dnsLogerController.OTHERGenerate(afTextArea4,DNSURL);

    }
    public void aeBtn(ActionEvent actionEvent) {
        String list_str = aeTextArea1.getText();
        AVRecognizeController avRecognizeController = new AVRecognizeController();
        avRecognizeController.AVCheck(aeTextArea2,list_str);
    }
    public void adBtn(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://0x7e.cc/SocialEngineeringDictionaryGenerator.html"));
    }
    public void caBtn1(ActionEvent actionEvent){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("选择");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[]{new FileChooser.ExtensionFilter("txt文本文件", new String[]{"*.txt"})});
        File chosenDir = chooser.showOpenDialog((Window) null);
        if (chosenDir != null) {
            String filePath = chosenDir.getAbsolutePath();
            this.save_path = filePath;
            this.urlList  = Tools.readFile(filePath);
            this.setTableView(this.urlList,this.catableView);
            this.caBtn2.setDisable(false);
            this.caBtn3.setDisable(false);
        }
    }
    public void acBtn(ActionEvent actionEvent){
        ReverseShellController reverseShellController = new ReverseShellController();
        HashMap<String, String> sourceHashmap = new HashMap<>();
        sourceHashmap.put("ip", this.acTextField1.getText());
        sourceHashmap.put("port", this.acTextField2.getText());
        TextArea[] TextAreas = {acTextArea1, acTextArea2, acTextArea3, acTextArea4, acTextArea5, acTextArea6, acTextArea7,
                acTextArea8, acTextArea9, acTextArea10, acTextArea11, acTextArea12, acTextArea13};
        String[] promptTexts = {acTextArea1.getPromptText(), acTextArea2.getPromptText(), acTextArea3.getPromptText(),
                acTextArea4.getPromptText(), acTextArea5.getPromptText(), acTextArea6.getPromptText(),
                acTextArea7.getPromptText(), acTextArea8.getPromptText(), acTextArea9.getPromptText(),
                acTextArea10.getPromptText(), acTextArea11.getPromptText(), acTextArea12.getPromptText(),
                acTextArea13.getPromptText()};
        for (int i = 0; i < TextAreas.length; i++) {
            TextArea textArea = TextAreas[i];
            String promptText = promptTexts[i];
            textArea.setText(reverseShellController.ReverseShell(sourceHashmap, promptText));
        }

    }
    public void abencodeBtn(ActionEvent actionEvent){
        CodingController codingController = new CodingController();
        String src = abTextArea1.getText();
        String select = abComboBox.getValue();
        if (select == null){
            abTextArea2.setText("请选择编码方式");
        }else if (select.equals("base64")){
            codingController.Base64Encode(this.abTextArea2,src);
        }else if (select.equals("unicode")){
            codingController.UnicodeEncode(this.abTextArea2,src);
        }else if (select.equals("url")){
            codingController.URLEncoder(this.abTextArea2,src);
        }else if (select.equals("字符串反转")){
            codingController.ReverseString(this.abTextArea2,src);
        }else if(select.equals("ASCII")){
            codingController.ASCIIEncoding(this.abTextArea2,src);
        }else if (select.equals("rot13")){
            codingController.rot13Encode(this.abTextArea2,src);
        }else if(select.equals("摩斯密码")){
            codingController.morseEncode(this.abTextArea2,src);
        }
    }
    public void abdecodeBtn(ActionEvent actionEvent){
        CodingController codingController = new CodingController();
        String src = abTextArea2.getText();
        String select = abComboBox.getValue();
        if (select == null){
            abTextArea1.setText("请选择解码方式");
        }else if (select.equals("base64")){
            codingController.Base64Decode(this.abTextArea1,src);
        }else if (select.equals("unicode")){
            codingController.UnicodeDecode(this.abTextArea1,src);
        }else if (select.equals("url")){
            codingController.URLDecoder(this.abTextArea1,src);
        }else if (select.equals("字符串反转")){
            codingController.ReverseString(this.abTextArea1,src);
        }else if(select.equals("ASCII")){
            codingController.ASCIIDecoding(this.abTextArea1,src);
        }else if (select.equals("rot13")){
            codingController.rot13Encode(this.abTextArea1,src);
        }else if(select.equals("摩斯密码")){
            codingController.morseDecode(this.abTextArea1,src);
        }

    }

    public void aabBtn(ActionEvent actionEvent) {
        HashMap<String, String> sourceHashmap = new HashMap<>();
        sourceHashmap.put("ip", this.aabip.getText());
        sourceHashmap.put("port", this.aabport.getText());
        sourceHashmap.put("srcfile", this.aabsrcfile.getText());
        sourceHashmap.put("dstfile", this.aabdstfile.getText());
        BaseToolsController baseToolsController = new BaseToolsController();

        TextField[] textFields = {aabInput1, aabInput2, aabInput3, aabInput4, aabInput5, aabInput6, aabInput7,
                aabInput8, aabInput9, aabInput10, aabInput11, aabInput12, aabInput13, aabInput14};
        String[] promptTexts = {aabInput1.getPromptText(), aabInput2.getPromptText(), aabInput3.getPromptText(),
                aabInput4.getPromptText(), aabInput5.getPromptText(), aabInput6.getPromptText(),
                aabInput7.getPromptText(), aabInput8.getPromptText(), aabInput9.getPromptText(),
                aabInput10.getPromptText(), aabInput11.getPromptText(), aabInput12.getPromptText(),
                aabInput13.getPromptText(), aabInput14.getPromptText()};

        for (int i = 0; i < textFields.length; i++) {
            TextField textField = textFields[i];
            String promptText = promptTexts[i];
            textField.setText(baseToolsController.UploadFileTools(sourceHashmap, promptText));
        }
    }

}