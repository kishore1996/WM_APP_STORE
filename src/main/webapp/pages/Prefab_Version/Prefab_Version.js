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

};

// Page.FileServiceUploadFile1onSuccess = function(variable, data) {
//     //console.log(data)
//     var i = 0;
//     var flag = 0;
//     var inter = setInterval(function() {
//         if (flag == 0) {
//             Page.Variables.insertInto_appscreenshots.setInput({
//                 "screenshots": data[i]["inlinePath"]
//             })
//             Page.Variables.insertInto_appscreenshots.invoke();
//             flag = 1;
//         }
//         if (check == 1) {
//             i = i + 1
//             flag = 0
//             check = 0
//         }
//         if (i == data.length) {
//             clearInterval(inter);
//         }
//     }, 1000)
// };

Page.save_buttonClick = function($event, widget) {
    if (Page.Widgets.text1.datavalue == null || Page.Widgets.textarea1.datavalue == null) {
        Page.Actions.nullnotify.invoke();
    } else if (Page.Widgets.fileupload2_1.selectedFiles.length == 0) {
        Page.Actions.fileupload.invoke();
    } else if (Page.Widgets.fileupload3.selectedFiles.length == 0) {
        Page.Actions.fileupload1.invoke();
    } else {
        Page.Variables.insertinto_appsource.invoke();
        Page.Variables.update_appinfo.invoke();
        Page.Actions.success.invoke();
        //Page.Actions.filesuploaded.invoke();
        //Page.Actions.timeaction_gotomain.invoke();
        //Page.Actions.gotomain.invoke();
    }
};
