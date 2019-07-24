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
    Page.Variables.selectAllVariable.invoke()
    // Page.Widgets.gridrow12.show = false;
    // Page.Widgets.gridrow16.show = false;
};

Page.select2Change = function($event, widget, newVal, oldVal) {
    if (newVal == "All") {
        Page.Variables.selectAllVariable.invoke()
        // Page.Widgets.gridrow4.show = true;
        // Page.Widgets.gridrow12.show = false;
        // Page.Widgets.gridrow16.show = false;
    }
    if (newVal == "Active") {
        Page.Variables.selectActiveVariable.invoke()
        // Page.Widgets.gridrow4.show = false;
        // Page.Widgets.gridrow12.show = true;
        // Page.Widgets.gridrow16.show = false;
    }
    if (newVal == "Inactive") {
        Page.Variables.selectInactiveVariable.invoke()
        // Page.Widgets.gridrow4.show = false;
        // Page.Widgets.gridrow12.show = false;
        // Page.Widgets.gridrow16.show = true;
    }
};

Page.notificationAction1onOk = function(variable, data) {
    console.log("HEllo")
    Page.Widgets.CredentialsLiveForm4.save();
};
Page.notificationAction3onOk = function(variable, data) {
    Page.Widgets.CredentialsLiveForm3.save();
};
Page.notificationAction2onOk = function(variable, data) {
    Page.Widgets.CredentialsLiveForm2.save();
};


Page.selectAllVariableonSuccess = function(variable, data) {
    Page.Widgets.gridrow4.show = true;
    Page.Widgets.gridrow12.show = false;
    Page.Widgets.gridrow16.show = false;
};
Page.selectInactiveVariableonSuccess = function(variable, data) {
    Page.Widgets.gridrow4.show = false;
    Page.Widgets.gridrow12.show = false;
    Page.Widgets.gridrow16.show = true;
};
Page.selectActiveVariableonSuccess = function(variable, data) {
    Page.Widgets.gridrow4.show = false;
    Page.Widgets.gridrow12.show = true;
    Page.Widgets.gridrow16.show = false;
};
