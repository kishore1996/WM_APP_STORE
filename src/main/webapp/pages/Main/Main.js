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

Page.continuebuttonClick = function($event, widget) {
    Page.Variables.insert_into_appinfo.invoke();
    Page.Actions.timerAction_prefab.invoke();
};
img = ["https://lh3.googleusercontent.com/8r-ZTPoTIywU_aK2OXmLKg5WOdzXRxv7UUpiIk7kY7Du12fXKDwxUb2M_vBFp4pPvmpK=s180-rw", "https://lh3.googleusercontent.com/nYhPnY2I-e9rpqnid9u9aAODz4C04OycEGxqHG5vxFnA35OGmLMrrUmhM9eaHKJ7liB-=s180-rw"]
var jsondata = []
Page.WM_APP_STOREAppInfoDataonSuccess = function(variable, data) {
    console.log(data)
    for (var i = 0; i < data.length; i++) {
        jsondata.push({
            "PrefabID": data[i]["id"],
            "category": data[i]["categoryId"],
            "CreatedBy": data[i]["createdBy"],
            "CreatedOn": data[i]["creationDate"],
            "Icon": data[i]["image"],
            "PrefabName": data[i]["name"]
        })
    }
    console.log(jsondata)
    Page.Variables.Prefabs.setData(jsondata)

};
Page.card1Click = function($event, widget, item, currentItemWidgets) {
    console.log(item)
    Page.App.Actions.goToPage_Prefab_Preview.setData({
        "ID": 1
    })
    Page.App.Actions.goToPage_Prefab_Preview.invoke()
};
