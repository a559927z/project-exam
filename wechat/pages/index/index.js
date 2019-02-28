//index.js
//获取应用实例
// 引入整个项目的app.js文件，用来取期中的公共变量等信息。
const app = getApp()

Page({
    // 就是本页面组件的内部数据，会渲染到该页面的wxml文件中，类似于vue、react~
    // 通过setData修改data数据，驱动页面渲染
    data: {
        // motto: '百斩题',
        userInfo: {},
        hasUserInfo: false,
        canIUse: wx.canIUse('button.open-type.getUserInfo'),
        // baiDu: {}
    },
    //事件处理函数
    clickWxLogin: function () {
        if (app.globalData.userInfo) {
            this.setData({
                userInfo: app.globalData.userInfo,
                hasUserInfo: true,
                baiDu: app.globalData.baiDu
            })
        } else if (this.data.canIUse) {
            // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
            // 所以此处加入 callback 以防止这种情况
            app.userInfoReadyCallback = res => {
                this.setData({
                    userInfo: res.userInfo,
                    hasUserInfo: true
                })
            }
        } else {
            // 在没有 open-type=getUserInfo 版本的兼容处理
            wx.getUserInfo({
                success: res => {
                    app.globalData.userInfo = res.userInfo;
                    // console.log(res);
                    this.setData({
                        userInfo: res.userInfo,
                        hasUserInfo: true
                    })
                }
            })
        }
        wx.navigateTo({
            url: '../wxlogin/wxlogin'
        })
    },
    // 监听页面加载、
    onLoad: function () {
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