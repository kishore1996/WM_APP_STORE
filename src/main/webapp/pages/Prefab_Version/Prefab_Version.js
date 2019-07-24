/*
 * Use App.getDependency for Dependency Injection
 * eg: var DialogService = App.getDependency('DialogService');
 */

/* perform any action on widgets/variables within this block */
Page.onReady = function() {
    /*
     * variables can be accessed through 'Page.Variables' property here
     * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
     * Page.Variables.loggedInUser.getData()
     *
     * widgets can be accessed through 'Page.Widgets' property here
     * e.g. to get value of text widget named 'username' use following script
     * 'Page.Widgets.username.datavalue'
     */
    Page.Variables.database_appinfo.setFilter({
        "id": Page.App.Variables.AppInfoID.dataSet
    })
    Page.Variables.database_appinfo.invoke()
    Page.Variables.database_appsource.setFilter({
        "appInfoId": parseInt(Page.App.Variables.AppInfoID.dataSet)
    })
    Page.Variables.database_appsource.invoke()
};

var check = 1;
Page.insertInto_appscreenshotsonSuccess = function(variable, data) {
    //console.log(data)
    if (data != null)
        check = 1;
    else
        check = 0;
};

Page.FileServiceUploadFile1onSuccess = function(variable, data) {
    //console.log(data)
    var i = 0;
    var flag = 0;
    var inter = setInterval(function() {
        if (flag == 0) {
            Page.Variables.insert_screenshots.setInput({
                "screenshots": data[i]["inlinePath"]
            })
            Page.Variables.insert_screenshots.invoke();
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


var no;
Page.database_appsourceonSuccess = function(variable, data) {};
Page.FileServiceUploadFileonSuccess = function(variable, data) {
    console.log("FIle")
    console.log(data)
    Page.save_buttonClick = function($event, widget) {
        if (Page.Widgets.description.datavalue == null) {
            Page.Actions.nullnotify.invoke();
        } else if (Page.Widgets.prefabupload.selectedFiles.length == 0) {
            Page.Actions.fileupload.invoke();
        } else if (Page.Widgets.screenshotsupload.selectedFiles.length == 0) {
            Page.Actions.fileupload1.invoke();
        } else {
            if (Page.Variables.FileServiceUploadFile.dataSet.length > 0) {
                d = Page.Variables.database_appsource.dataSet[0]
                d["fileName"] = data[0]["fileName"]
                d["filePath"] = data[0]["inlinePath"]
                if (d["versionId"] == null) {
                    d["versionId"] = 1
                } else {
                    d["versionId"] = d["versionId"] + 1
                }
                Page.Variables.insert_appsource.createRecord({
                    row: d
                })
                // Page.Variables.update_appinfo.invoke();
                Page.Actions.success.invoke();
            }
        }
    };
};
