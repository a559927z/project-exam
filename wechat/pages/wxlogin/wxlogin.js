//index.js
//获取应用实例
// 引入整个项目的app.js文件，用来取期中的公共变量等信息。
const app = getApp()

Page({
    // 就是本页面组件的内部数据，会渲染到该页面的wxml文件中，类似于vue、react~
    // 通过setData修改data数据，驱动页面渲染
    data: {
        userInfo: {},
        hasUserInfo: false,
        canIUse: wx.canIUse('button.open-type.getUserInfo'),
    },

    // 监听页面加载、
    onLoad: function () {
        this.setData({
            userInfo: app.globalData.userInfo,
            hasUserInfo: true
        })
        console.log(this.data.userInfo);
    },
    // 页面初次渲染、
    onReady() {
    },
    // 页面显示、
    onShow() {
    },
    // 页面隐藏等等
    onHide() {
    },
    // 设置页面分享的信息
    onShareAppMessage() {
    },

    // getUserInfo: function (e) {
    //     console.log(e)
    //     app.globalData.userInfo = e.detail.userInfo
    //     this.setData({
    //         userInfo: e.detail.userInfo,
    //         hasUserInfo: true
    //     })
    // }
})
