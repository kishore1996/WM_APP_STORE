/*
 * Use App.getDependency for Dependency Injection
 * eg: var DialogService = App.getDependency('DialogService');
 */

/* perform any action on widgets/variables within this block */
Partial.onReady = function() {
    /*
     * variables can be accessed through 'Partial.Variables' property here
     * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
     * Partial.Variables.loggedInUser.getData()
     *
     * widgets can be accessed through 'Partial.Widgets' property here
     * e.g. to get value of text widget named 'username' use following script
     * 'Partial.Widgets.username.datavalue'
     */
};
Partial.orderLinkClick = function($event, widget) {
    App.Variables.staticFilterVariable.setData({
        "name": "1"
    })

    console.log(App.Variables.staticFilterVariable.getData().name)
};
Partial.appslinkClick = function($event, widget) {

};

Partial.mapslinkClick = function($event, widget) {

};

Partial.medialinkClick = function($event, widget) {

};
Partial.newslinkClick = function ($event, widget) { 

};

Partial.travellinkClick = function ($event, widget) { 

};

Partial.basiclinkClick = function ($event, widget) { 

};
