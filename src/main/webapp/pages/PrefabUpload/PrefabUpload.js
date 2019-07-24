/* perform any action on widgets within this block */
Page.onReady = function() {
    Page.Widgets.dialog1.open();
    /*
     * widgets can be accessed through 'Page.Widgets' property here
     * e.g. to get value of text widget named 'username' use following script
     * 'Page.Widgets.username.datavalue'
     */
};

Page.continue_dialogClick = function($event, widget) {

    if (Page.Widgets.prefabname.datavalue == null || Page.Widgets.description.datavalue == null) {
        Page.Actions.nuotnullnotify.invoke();
        //Page.Widgets.dialog1.open();
    }
    if (Page.Widgets.select1.datavalue == null) {
        Page.Actions.selectcategory_alert.invoke();
    }
    if (Page.Widgets.icon_upload.selectedFiles.length == 0) {
        Page.Actions.selectfileupload.invoke();
    } else {
        Page.Variables.appname.invoke();
        // Page.Variables.insertInto_appinfo.invoke();
        // Page.Widgets.dialog1.close();
        // Page.Actions.timerAction1.invoke();
        // Page.Actions.prefab_created.invoke();
    }
    //console.log(Page.Widgets.icon_upload.selectedFiles)
};

//checking the duplicate pre-fab name

Page.appnameonSuccess = function(variable, data) {

    //console.log(data)
    var f = 1;
    var un = Page.Widgets.prefabname.datavalue;
    // console.log(un)
    // console.log(data.length)
    // console.log(data[0].name)
    // console.log(data[1].name)
    // console.log(data[2].name)
    for (var i = 0; i < data.length; i++) {
        if (un == data[i].name) {
            Page.Actions.prafbnamealready.invoke();
            f = 0;
            break;
        }
    }
    if (f == 1) {
        Page.Variables.FileServiceUploadFile3.invoke();
        Page.FileServiceUploadFile3onSuccess = function(variable, data) {
            Page.Variables.insertInto_appinfo.invoke();
            Page.Widgets.dialog1.close();
            //Page.Actions.timerAction1.invoke();
            //Page.Actions.prefab_created.invoke();
        };
    }
};

//
//
//This is for the prefab page
//
//
//

var check = 1;

Page.insertInto_appscreenshotsonSuccess = function(variable, data) {
    if (data != null)
        check = 1;
    else
        check = 0;
};


Page.saveClick = function($event, widget) {
    if (Page.Widgets.prefab_upload.selectedFiles.length == 0) {
        Page.Actions.selectfileupload.invoke();
    }
    if (Page.Widgets.screenshot_upload.selectedFiles.length == 0) {
        Page.Actions.selectfileupload.invoke();
    } else {
        Page.Variables.FileServiceUploadFile1.invoke();
    }
};

Page.FileServiceUploadFile1onSuccess = function(variable, data) {
    var i = 0;
    var flag = 0;
    var inter = setInterval(function() {
        if (flag == 0) {
            Page.Variables.insertInto_appscreenshots.setInput({
                "screenshots": data[i]["inlinePath"]
            })
            Page.Variables.insertInto_appscreenshots.invoke();
            flag = 1;
        }
        if (check == 1) {
            i = i + 1
            flag = 0
            check = 0
        }
        if (i == data.length) {
            clearInterval(inter);
        }
    }, 1000)
    Page.Variables.FileServiceUploadFile.invoke();
};

Page.FileServiceUploadFileonSuccess = function(variable, data) {
    Page.Variables.insertINto_appsource.invoke();
    Page.Actions.prefab_created.invoke();
};

Page.prefab_createdonClick = function(variable, data) {
    Page.Actions.gotomain.invoke();
};
Page.prefab_createdonHide = function(variable, data) {
    Page.Actions.gotomain.invoke();
};
