/*
 * Use App.getDependency for Dependency Injection
 * eg: var DialogService = App.getDependency('DialogService');
 */

/* perform any action on widgets/variables within this block */
var CurrentUser;

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
    Page.Variables.AppRatings.setFilter({
        "appInfoId": Page.App.Variables.AppInfoID.dataSet
    });
    Page.Variables.Appinfo.setFilter({
        "id": Page.App.Variables.AppInfoID.dataSet
    })
    Page.Variables.AppScreenshots.setFilter({
        "appInfoId": Page.App.Variables.AppInfoID.dataSet
    })
    console.log(Page.App.Variables.loggedInUser.dataSet)
    console.log("-" + Page.App.Variables.AppInfoID.dataSet + "-")

    if (Page.App.Actions.goToPage_Prefab_Preview.dataSet.ID == null)
        Page.App.Actions.goToPage_Main.invoke()
    var id = Page.App.Actions.goToPage_Prefab_Preview.dataSet.ID;
    CurrentUser = (Page.App.Variables.loggedInUser.dataSet.name)

    Page.Widgets.submit.display = "none";
    Page.Widgets.textarea1.display = "none";
    Page.Widgets.label3.display = "none";
    Page.Widgets.userrating.display = "none";
    Page.Widgets.edit.display = "none";
    Page.Widgets.edit1.display = "none";
    Page.Widgets.save.display = "none";
    Page.Widgets.save1.display = "none";
    Page.Widgets.cancel.display = "none";
    Page.Widgets.cancel1.display = "none";
    Page.Widgets.button15.display = "none";
};
var temp;
var creator = false;
Page.editClick = function($event, widget) {
    Page.Widgets.text2.readonly = false;
    Page.Widgets.edit.display = "none";
    Page.Widgets.save.display = "";
    Page.Widgets.cancel.display = "";
    temp = Page.Widgets.text2.datavalue;

};
Page.saveClick = function($event, widget) {
    Page.Widgets.text2.readonly = true;
    Page.Widgets.save.display = "none";
    Page.Widgets.cancel.display = "none";
    Page.Widgets.edit.display = "";
    d = Page.Variables.Appinfo.dataSet[0]
    d["name"] = Page.Widgets.text2.datavalue;
    console.log(d)
    Page.Variables.App_Info_Update.updateRecord({
        row: d
    })
};
Page.cancelClick = function($event, widget) {
    Page.Widgets.text2.datavalue = temp;
    temp = null;
    Page.Widgets.text2.readonly = true;
    Page.Widgets.save.display = "none";
    Page.Widgets.cancel.display = "none";
    Page.Widgets.edit.display = "";

};
Page.edit1Click = function($event, widget) {
    Page.Widgets.textarea2.readonly = false;
    Page.Widgets.edit1.display = "none";
    Page.Widgets.save1.display = "";
    Page.Widgets.cancel1.display = "";
    temp = Page.Widgets.textarea2.datavalue;
};

Page.save1Click = function($event, widget) {
    Page.Widgets.textarea2.readonly = true;
    Page.Widgets.save1.display = "none";
    Page.Widgets.cancel1.display = "none";
    Page.Widgets.edit1.display = "";
    d = Page.Variables.Appinfo.dataSet[0]
    d["_desc"] = Page.Widgets.textarea2.datavalue;
    console.log(d)
    Page.Variables.App_Info_Update.updateRecord({
        row: d
    })
};

Page.cancel1Click = function($event, widget) {
    Page.Widgets.textarea2.datavalue = temp;
    temp = null;
    Page.Widgets.textarea2.readonly = true;
    Page.Widgets.save1.display = "none";
    Page.Widgets.cancel1.display = "none";
    Page.Widgets.edit1.display = "";

};
Page.submitClick = function($event, widget) {

    Page.Variables.stForReview.dataSet.selfreview.createdby = CurrentUser;
    Page.Variables.stForReview.dataSet.selfreview.rate = Page.Widgets.userrating.datavalue;
    Page.Variables.stForReview.dataSet.selfreview.comment = Page.Widgets.textarea1.datavalue;
    var dateObj = new Date();
    var month = dateObj.getUTCMonth() + 1;
    var day = dateObj.getUTCDate();
    var year = dateObj.getUTCFullYear();
    var newdate = day + "/" + month + "/" + year;
    Page.Variables.stForReview.dataSet.selfreview.createddate = newdate

    Page.Widgets.submit.display = "none";
    Page.Widgets.textarea1.display = "none";
    Page.Widgets.label3.display = "none";
    Page.Widgets.userrating.display = "none";
    Page.Widgets.stnewJsonList1_1.display = "";

    Page.Variables.AppRating_Update.createRecord({
        row: {
            "appInfoId": Page.Variables.Appinfo.dataSet[0]["id"],
            "createdBy": CurrentUser,
            "comments": Page.Widgets.textarea1.datavalue,
            "creationDate": new Date(),
            "lastUpdateDate": new Date(),
            "rate": Page.Widgets.userrating.datavalue,
            "updatedBy": CurrentUser
        }
    })


};

var comments;
Page.okprefabClick = function($event, widget, item, currentItemWidgets) {
    Page.Widgets.okprefab.display = "none";
    Page.Widgets.closeprefab.display = "none";
    Page.Widgets.textarea12.readonly = true;
    Page.Widgets.editprefab.display = "";
    Page.Widgets.deleteprefab.display = "";
    comments = Page.Widgets.textarea12.datavalue;
    Page.Variables.stForReview.dataSet.selfreview.rate = Page.Widgets.rating.datavalue;
    Page.Variables.stForReview.dataSet.selfreview.comment = comments;
    console.log(Page.Variables.AppRatings.dataSet)
    data = Page.Variables.AppRatings.dataSet
    for (var i = 0; i < data.length; i++) {
        if (data[i]["appInfoId"] == Page.Variables.Appinfo.dataSet[0]["id"])
            break
    }
    data[i]["comments"] = Page.Widgets.textarea12.datavalue;
    data[i]["rate"] = Page.Widgets.rating.datavalue;
    Page.Variables.AppRating_Update.updateRecord({
        row: data[i]
    })

};

Page.closeprefabClick = function($event, widget, item, currentItemWidgets) {
    Page.Widgets.textarea12.datavalue = comments;
    comments = null;
    Page.Widgets.textarea12.readonly = true;
    Page.Widgets.okprefab.display = "none";
    Page.Widgets.closeprefab.display = "none";
    Page.Widgets.editprefab.display = "";
    Page.Widgets.deleteprefab.display = "";
};

Page.editprefabClick = function($event, widget, item, currentItemWidgets) {

    Page.Widgets.editprefab.display = "none";
    Page.Widgets.deleteprefab.display = "none";
    Page.Widgets.okprefab.display = "";
    Page.Widgets.closeprefab.display = "";
    Page.Widgets.textarea12.readonly = false;
    comments = Page.Widgets.textarea12.datavalue;
    Page.Variables.stForReview.dataSet.selfreview.rate = Page.Widgets.rating.datavalue;
    Page.Variables.stForReview.dataSet.selfreview.comment = comments;
};


Page.deleteprefabClick = function($event, widget, item, currentItemWidgets) {
    Page.Widgets.stnewJsonList1_1.show = false;
    Page.Variables.stForReview.dataSet.selfreview = {};
    Page.Widgets.submit.display = "";
    Page.Widgets.textarea1.display = "";
    Page.Widgets.label3.display = "";
    Page.Widgets.userrating.display = "";
    Page.Widgets.textarea1.datavalue = "";
    Page.Widgets.userrating.datavalue = 0;
    for (var i = 0; i < data.length; i++) {
        if (data[i]["appInfoId"] == Page.Variables.Appinfo.dataSet[0]["id"])
            break
    }
    Page.Variables.AppRating_Update.deleteRecord({
        row: {
            "id": data[i]["id"]
        }
    })
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
    if (CurrentUser == data[0]["createdBy"] || CurrentUser == "admin")
        creator = true
    if (creator) {
        Page.Widgets.edit.display = "";
        Page.Widgets.edit1.display = "";
        // Page.Widgets.button15.show = true;
    }
    Page.Variables.PrefabSource.setInput({
        "id": data[0]["id"]
    })
    Page.Variables.PrefabSource.invoke()
    Page.Variables.AvgRating.setInput({
        "APPID": data[0]["id"]
    })
    console.log("Description")
    console.log(data[0]["_desc"])
    console.log(Page.Variables.PrefabData.dataSet)
    Page.Variables.AvgRating.invoke()
    Page.Variables.PrefabData.dataSet["Name"] = data[0]["name"]
    Page.Variables.PrefabData.dataSet["icon"] = data[0]["image"]
    if (data[0]["mdCategory"] != null)
        Page.Variables.PrefabData.dataSet["categoryID"] = data[0]["mdCategory"]["label"]
    else
        Page.Variables.PrefabData.dataSet["categoryID"] = null
    Page.Variables.PrefabData.dataSet["Description"] = data[0]["_desc"]

};

Page.AvgRatingonSuccess = function(variable, data) {
    console.log("avg")
    console.log(data)
    console.log(data[0]["avg_rate_"] != null)

    if (data[0]["avg_rate_"] != null) {
        Page.Widgets.rating1.selectedRatingValue = data[0]["avg_rate_"]
        Page.Widgets.rating1.datavalue = data[0]["avg_rate_"]
        Page.Widgets.rating1.caption = Math.round(data[0]["avg_rate_"])

    } else {
        Page.Widgets.rating1.selectedRatingValue = 0
        Page.Widgets.rating1.datavalue = 0
        Page.Widgets.rating1.caption = null
    }
    console.log(Page.Widgets.rating1)
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
    console.log("caurosel")
    console.log(Page.Widgets.carousel1.dataset)
    console.log("rating")
    console.log(data)
    d = []
    e = {}
    flag = false;
    for (var i = 0; i < data.length; i++) {
        if (data[i]["createdBy"] != CurrentUser)
            d.push({
                "rate": data[i]["rate"],
                "comment": data[i]["comments"],
                "createdby": data[i]["createdBy"],
                "createddate": data[i]["creationDate"]
            })
        else {
            e = {
                "rate": data[i]["rate"],
                "comment": data[i]["comments"],
                "createdby": data[i]["createdBy"],
                "createddate": data[i]["creationDate"]
            }
            flag = true;
        }
    }
    console.log("success rating")
    console.log(d)
    console.log(e)
    if (!flag) {
        Page.Widgets.submit.display = "";
        Page.Widgets.textarea1.display = "";
        Page.Widgets.label3.display = "";
        Page.Widgets.userrating.display = "";
    }
    Page.Variables.stForReview.dataSet["reviews"] = d
    Page.Variables.stForReview.dataSet["selfreview"] = e
};

Page.App_Info_UpdateonSuccess = function(variable, data) {

};
Page.button15Click = function($event, widget) {
    data = Page.Variables.AppScreenshots.dataSet
    var i;
    for (i = 0; i < data.length; i++) {
        if (data[i]["screenshots"] == Page.Widgets.picture4.picturesource) {
            Page.Widgets.picture4.picturesource = null
            break;
        }
    }
    console.log(Page.Variables.AppScreenshots.dataSet)

    console.log(Page.Variables.AppScreenshots.dataSet)
    Page.Variables.App_Screenshots.deleteRecord({
        row: {
            "id": data[i]["id"]
        }
    })
    for (var j = 0; j < Page.Widgets.carousel1.dataset.length; j++) {
        if (Page.Widgets.carousel1.dataset[j]["image"] == data[i]["screenshots"]) {
            delete Page.Variables.AppScreenshots.dataSet[j]
            break
        }
    }
    console.log(data[i]["id"])
    console.log(Page.Widgets.picture4.picturesource)
};

Page.App_ScreenshotsonSuccess = function(variable, data) {

};

Page.AppRating_UpdateonSuccess = function(variable, data) {

};
Page.button1Click = function($event, widget) {
    window.open(Page.Variables.PrefabSource.dataSet[Page.Variables.PrefabSource.dataSet.length - 1]["filePath"])
    window.focus()
};


Page.Revert_updateonOk = function(variable, data) {
    console.log("Revert")
    console.log(data)
    console.log(Page.Variables.PrefabSource.dataSet)
    d = Page.Variables.PrefabSource.dataSet
    if (d.length > 0)
        Page.Variables.APP_SOURCE.deleteRecord({
            row: d[d.length - 1]["id"]
        })
    else {
        //info source ,screenshots downloadhistory rating
        Page.Variables
    }
};

Page.button14Click = function($event, widget) {
    Page.Variables.PrefabSource.invoke();
};