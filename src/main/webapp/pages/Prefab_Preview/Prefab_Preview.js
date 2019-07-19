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