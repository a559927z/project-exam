//index.js
//获取应用实例
// 引入整个项目的app.js文件，用来取期中的公共变量等信息。
const app = getApp();
const util = require('../../utils/util.js');

Page({
    // 就是本页面组件的内部数据，会渲染到该页面的wxml文件中，类似于vue、react~
    // 通过setData修改data数据，驱动页面渲染
    data: {
        // motto: '百斩题',
        userInfo: {},
        hasUserInfo: false,
        canIUse: wx.canIUse('button.open-type.getUserInfo')
    },
    clickLogin: function () {

    },
    //事件处理函数
    clickWxLogin: function (e) {
        this.login();
        // let sessionKey = wx.getStorageSync('sessionKey');
        // if (null != sessionKey) {
        //     if (app.globalData.userInfo) {
        //         this.setData({
        //             userInfo: app.globalData.userInfo,
        //             hasUserInfo: true,
        //         })
        //     } else if (this.data.canIUse) {
        //         // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
        //         // 所以此处加入 callback 以防止这种情况
        //         app.userInfoReadyCallback = res => {
        //             this.setData({
        //                 userInfo: res.userInfo,
        //                 hasUserInfo: true
        //             })
        //         }
        //     } else {
        //         // 在没有 open-type=getUserInfo 版本的兼容处理
        //         wx.getUserInfo({
        //             success: res => {
        //                 app.globalData.userInfo = res.userInfo;
        //
        //             }
        //         })
        //     }
        //     wx.navigateTo({
        //         url: '../wxlogin/wxlogin'
        //     })
        // }
    },
    wxLogin() {
        this.login();
    },
    /**
     * 登录
     *  1:wx.login 得到 session_key
     *  2:wx.userInfo 得到 encryptedData、iv
     *  3:session_key、encryptedData、iv提交后台解密 得到 unionId
     */
    login() {
        wx.login({
            success: res => {
                let thisPage = this;
                // ------ 获取凭证 ------
                let code = res.code;
                if (code) {
                    // console.log('获取用户登录凭证：' + code);
                    // ------ 发送凭证 ------
                    util.wxAjax(util.uri.login, {code: code}, function (res) {
                        if (res.statusCode == 200) {
                            wx.setStorageSync("sessionKey", res.data.data.session_key);
                            wx.setStorageSync('openid', res.data.data.openId);
                            let sessionKey = wx.getStorageSync("sessionKey");
                            // ------ 获取用户基本信息 ------
                            wx.getUserInfo({
                                success: res => {
                                    let encryptedData = res.encryptedData;
                                    let iv = res.iv;
                                    // ------ 数据签名校验，解密得unionId ------
                                    // https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/signature.html
                                    util.wxAjax(
                                        util.uri.findUserInfo,
                                        {"encryptedData": encryptedData, "sessionKey": sessionKey, "iv": iv},
                                        function (res) {
                                            if (res.statusCode == 200) {
                                                console.log('解密后 data: ', res.data.data);
                                                thisPage.setData({
                                                    userInfo: res.data.data,
                                                    hasUserInfo: true
                                                });
                                                wx.navigateTo({
                                                    url: '../wxlogin/wxlogin'
                                                })
                                            } else {
                                                console.error(res.errMsg)
                                            }
                                        });
                                }
                            })
                        } else {
                            console.error(res.errMsg)
                        }
                    });
                } else {
                    console.error('获取用户登录失败：' + res.errMsg);
                }
            }
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
    }
    // getUserInfo: function (e) {
    //     console.log(e)
    //     app.globalData.userInfo = e.detail.userInfo
    //     this.setData({
    //         userInfo: e.detail.userInfo,
    //         hasUserInfo: true
    //     })
    // }
});