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
    var jsondata = []
    for (var i = 0; i < data.length; i++) {
        console.log(data[i].mdCategory.label)
        jsondata.push({
            "PrefabID": data[i]["id"],
            "category": data[i].mdCategory.label,
            "CreatedBy": data[i]["createdBy"],
            "CreatedOn": data[i]["creationDate"],
            "Icon": data[i]["image"],
            "PrefabName": data[i]["name"]
        })
    }
    console.log(jsondata)
    Page.Variables.Prefabs.setData(jsondata)

};
console.log(jsondata)
Page.card1Click = function($event, widget, item, currentItemWidgets) {
    console.log(item)
    Page.App.Variables.AppInfoID.setData(item["PrefabID"])
    Page.App.Actions.goToPage_Prefab_Preview.setData({
        "ID": item["PrefabID"]
    })
    Page.App.Actions.goToPage_Prefab_Preview.invoke()
};

Page.selectPrefabsFilteredVariableonSuccess = function(variable, data) {
    console.log(data)
    var jsondata = []
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
Page.anchor4Click = function($event, widget) {
    Page.Variables.selectPrefabsFilteredVariable.setFilter({
        "categoryId": 1
    })
    Page.Variables.selectPrefabsFilteredVariable.invoke()
};
Page.anchor3Click = function($event, widget) {
    Page.Variables.WM_APP_STOREAppInfoData.invoke()
};
Page.anchor5Click = function($event, widget) {
    Page.Variables.selectPrefabsFilteredVariable.setFilter({
        "categoryId": 2
    })
    Page.Variables.selectPrefabsFilteredVariable.invoke()
};
Page.anchor6Click = function($event, widget) {
    Page.Variables.selectPrefabsFilteredVariable.setFilter({
        "categoryId": 3
    })
    Page.Variables.selectPrefabsFilteredVariable.invoke()
};
Page.anchor7Click = function($event, widget) {
    Page.Variables.selectPrefabsFilteredVariable.setFilter({
        "categoryId": 4
    })
    Page.Variables.selectPrefabsFilteredVariable.invoke()
};
Page.anchor8Click = function($event, widget) {
    Page.Variables.selectPrefabsFilteredVariable.setFilter({
        "categoryId": 5
    })
    Page.Variables.selectPrefabsFilteredVariable.invoke()
};

Page.search2Submit = function($event, widget) {
    console.log(widget.result)
    jsondata = []
    var data = widget.result;
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
