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
    } else {
        Page.Variables.insertInto_appinfo.invoke();
        Page.Widgets.dialog1.close();
        Page.Actions.timerAction1.invoke();
        Page.Actions.prefab_created.invoke();
    }
};

var check = 1;

Page.insertInto_appscreenshotsonSuccess = function(variable, data) {
    console.log(data)
    if (data != null)
        check = 1;
    else
        check = 0;
};

Page.FileServiceUploadFile1onSuccess = function(variable, data) {
    console.log(data)
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
};

Page.saveClick = function($event, widget) {
    if (Page.Widgets.text3.datavalue == null) {
        Page.Actions.nuotnullnotify.invoke();
    } else {
        Page.Variables.insertINto_appsource.invoke();
        Page.Actions.filesuploaded.invoke();
        Page.Actions.timeaction_gotomain.invoke();
        Page.Actions.gotomain.invoke();
    }
};
