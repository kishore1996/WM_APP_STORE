/*
 * Use App.getDependency for Dependency Injection
 * eg: var DialogService = App.getDependency('DialogService');
 */
var user = Page.App.Variables.loggedInUser.getData()
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
    console.log(user["name"])
    Page.Variables.viewProfileVariable.setInput({
        "uname": user["name"]
    })
    Page.Variables.viewProfileVariable.invoke()

};

Page.tabpane1Select = function($event, widget) {
    Page.Variables.viewProfileVariable.setInput({
        "uname": user["name"]
    })
    Page.Variables.viewProfileVariable.invoke()
};
