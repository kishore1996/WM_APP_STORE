/* perform any action on widgets within this block */
Page.onReady = function() {
    Page.Widgets.dialog1.open();
    /*
     * widgets can be accessed through 'Page.Widgets' property here
     * e.g. to get value of text widget named 'username' use following script
     * 'Page.Widgets.username.datavalue'
     */
};
Page.saveClick = function($event, widget) {
    if (Page.Widgets.text3.datavalue == null) {
        Page.Actions.nuotnullnotify.invoke();
    } else {
        Page.Variables.insert_into_screenshot.invoke();
    }
};
Page.continue_dialogClick = function($event, widget) {
    if (Page.Widgets.prefabname.datavalue == null || Page.Widgets.description.datavalue == null) {
        Page.Actions.nuotnullnotify.invoke();
        Page.Widgets.dialog1.open();
    } else {
        Page.Variables.insert_into_appinfo.invoke();
        Page.Actions.timerAction1.invoke();
    }
};
