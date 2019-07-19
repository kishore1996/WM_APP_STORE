/* perform any action on widgets within this block */
Page.onReady = function() {
    /*
     * widgets can be accessed through 'Page.Widgets' property here
     * e.g. to get value of text widget named 'username' use following script
     * 'Page.Widgets.username.datavalue'
     */
};

Page.saveClick = function($event, widget) {
    setInterval(function() {
        Page.Variables.insert_into_screenshot.invoke;
    }, 3000);
};