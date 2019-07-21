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
    console.log(Page.App.Actions.goToPage_Prefab_Preview.dataSet.ID == null)
    if (Page.App.Actions.goToPage_Prefab_Preview.dataSet.ID == null)
        Page.App.Actions.goToPage_Main.invoke()

    console.log(Page.Variables.PrefabData.dataSet)
    var id = Page.App.Actions.goToPage_Prefab_Preview.dataSet.ID;
    Page.Variables.PrefabData.dataSet = {}
    Page.Widgets.save.show = false;
    Page.Widgets.save1.show = false;
    Page.Widgets.cancel.show = false;
    Page.Widgets.cancel1.show = false;
};

function nameEdit() {
    alert("nameEdit")
}
var jsondata = {
    "Name": "PrefabName",
    "icon": "https://media.licdn.com/dms/image/C560BAQE9O_4idOVN0w/company-logo_200_200/0?e=2159024400&v=beta&t=NmS2mTzVU8MSm-UkeRwc4vFqyYaEoD2VMMpqSt5eEsg",
    "categoryID": "PrefabCategory",
    "AvgRating": "4",
    "TotalDownloads": "1000",
    "Images": [{
        "image": "https://www.wavemaker.com/wp-content/uploads/WM_blue-retina.png"
    }, {
        "image": "https://upload.wikimedia.org/wikipedia/en/4/4f/Wavemaker-logo.png"
    }, {
        "image": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6KqN0_ZSKCJTF8DZrwDUjCEZptomNz4UYr_6GXMYij02JAw8g"
    }, {
        "image": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwwJBE6ZNcwyvVj_vGrrI_20LlEFtX5EdKKBFwCi5TEwQT93NqPg"
    }],
    "Description": "Prefab  Description",

}
var temp;
Page.editClick = function($event, widget) {
    Page.Widgets.text2.readonly = false;
    Page.Widgets.edit.show = false;
    Page.Widgets.save.show = true;
    Page.Widgets.cancel.show = true;
    temp = Page.Widgets.text2.datavalue;
};
Page.saveClick = function($event, widget) {
    Page.Widgets.text2.readonly = true;
    Page.Widgets.save.show = false;
    Page.Widgets.cancel.show = false;
    Page.Widgets.edit.show = true;
};
Page.cancelClick = function($event, widget) {
    Page.Widgets.text2.datavalue = temp;
    temp = null;
    Page.Widgets.text2.readonly = true;
    Page.Widgets.save.show = false;
    Page.Widgets.cancel.show = false;
    Page.Widgets.edit.show = true;

};
Page.edit1Click = function($event, widget) {
    Page.Widgets.textarea2.readonly = false;
    Page.Widgets.edit1.show = false;
    Page.Widgets.save1.show = true;
    Page.Widgets.cancel1.show = true;
    temp = Page.Widgets.textarea2.datavalue;
};

Page.save1Click = function($event, widget) {
    Page.Widgets.textarea2.readonly = true;
    Page.Widgets.save1.show = false;
    Page.Widgets.cancel1.show = false;
    Page.Widgets.edit1.show = true;
};

Page.cancel1Click = function($event, widget) {
    Page.Widgets.textarea2.datavalue = temp;
    temp = null;
    Page.Widgets.textarea2.readonly = true;
    Page.Widgets.save1.show = false;
    Page.Widgets.cancel1.show = false;
    Page.Widgets.edit1.show = true;

};
Page.submitClick = function($event, widget) {
    Page.Widgets.stnewJsonList1_1.show;
    Page.Variables.stForReview.dataSet.selfreview.createdby = "CurrentUser";
    Page.Variables.stForReview.dataSet.selfreview.rate = Page.Widgets.userrating.datavalue;
    Page.Variables.stForReview.dataSet.selfreview.comment = Page.Widgets.textarea1.datavalue;
    var dateObj = new Date();
    var month = dateObj.getUTCMonth() + 1;
    var day = dateObj.getUTCDate();
    var year = dateObj.getUTCFullYear();
    var newdate = day + "/" + month + "/" + year;
    Page.Variables.stForReview.dataSet.selfreview.createddate = newdate;
    Page.Widgets.submit.show = false;
    Page.Widgets.textarea1.show = false;
    Page.Widgets.label3.show = false;
    Page.Widgets.userrating.show = false;

};

var comments;
Page.okprefabClick = function($event, widget, item, currentItemWidgets) {
    Page.Widgets.okprefab.show = false;
    Page.Widgets.closeprefab.show = false;
    Page.Widgets.textarea2.readonly = true;
    Page.Widgets.editprefab.show = true;
    Page.Widgets.deleteprefab.show = true;
    comments = Page.Widgets.textarea2.datavalue;
    Page.Variables.stForReview.dataSet.selfreview.rate = Page.Widgets.rating.datavalue;
    Page.Variables.stForReview.dataSet.selfreview.comment = comments;
};

Page.closeprefabClick = function($event, widget, item, currentItemWidgets) {
    Page.Widgets.textarea2.datavalue = comments;
    comments = null;
    Page.Widgets.textarea2.readonly = true;
    Page.Widgets.okprefab.show = false;
    Page.Widgets.closeprefab.show = false;
    Page.Widgets.editprefab.show = true;
    Page.Widgets.deleteprefab.show = true;
};

Page.editprefabClick = function($event, widget, item, currentItemWidgets) {
    Page.Widgets.okprefab.show = true;
    Page.Widgets.closeprefab.show = true;
    Page.Widgets.textarea2.readonly = false;
    Page.Widgets.editprefab.show = false;
    Page.Widgets.deleteprefab.show = false;
    comments = Page.Widgets.textarea2.datavalue;
    Page.Variables.stForReview.dataSet.selfreview.rate = Page.Widgets.rating.datavalue;
    Page.Variables.stForReview.dataSet.selfreview.comment = comments;
};


Page.deleteprefabClick = function($event, widget, item, currentItemWidgets) {
    Page.Widgets.stnewJsonList1_1.show = false;
    Page.Variables.stForReview.dataSet.selfreview = {};
    Page.Widgets.submit.show = true;
    Page.Widgets.textarea1.show = true;
    Page.Widgets.label3.show = true;
    Page.Widgets.userrating.show = true;
    Page.Widgets.textarea1.datavalue = " ";
    Page.Widgets.userrating.datavalue = 0;
};

Page.PrefabSourceonSuccess = function(variable, data) {
    console.log("source")
    console.log(data)
    Page.Variables.TotalDownloads.setInput({
        "id": data[0]["id"]
    })
    Page.Variables.TotalDownloads.invoke()
};
Page.TotalDownloadsonSuccess = function(variable, data) {
    console.log("totaldownloads")
    console.log(data)
    Page.Variables.PrefabData.dataSet["TotalDownloads"] = data[0]["count___"]
};

Page.AppinfoonSuccess = function(variable, data) {
    console.log("Info")
    console.log(data)
    Page.Variables.PrefabSource.setInput({
        "id": data[0]["id"]
    })
    Page.Variables.PrefabSource.invoke()
    Page.Variables.AvgRating.setInput({
        "APPID": data[0]["id"]
    })
    Page.Variables.AvgRating.invoke()
    Page.Variables.PrefabData.dataSet["Name"] = data[0]["name"]
    Page.Variables.PrefabData.dataSet["icon"] = data[0]["image"]
    Page.Variables.PrefabData.dataSet["categoryID"] = data[0]["mdCategory"]["label"]
    Page.Variables.PrefabData.dataSet["Description"] = data[0]["_desc"]
};

Page.AvgRatingonSuccess = function(variable, data) {
    console.log("avg")
    console.log(data)
    Page.Variables.PrefabData.dataSet["AvgRating"] = data["avg_rate_"]
};

Page.AppScreenshotsonSuccess = function(variable, data) {
    console.log('screen')
    console.log(data)
    d = []
    for (var i = 0; i < data.length; i++) {
        d.push({
            "image": data[i]["screenshots"]
        })
    }
    Page.Variables.PrefabData.dataSet["Images"] = d;
};

Page.AppRatingsonSuccess = function(variable, data) {
    console.log("rating")
    console.log(data)
    d = []
    for (var i = 0; i < data.length; i++) {
        d.push({
            "rate": data[i]["rate"],
            "comment": data[i]["comments"],
            "createdby": data[i]["createdBy"],
            "createddate": data[i]["creationDate"]
        })
    }
    Page.Variables.stForReview.dataSet["reviews"] = d
};