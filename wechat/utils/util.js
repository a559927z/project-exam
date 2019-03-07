//获得接口地址
const getRequestUrl = "http://127.0.0.1:8080/exam";


const formatTime = date => {
    const year = date.getFullYear()
    const month = date.getMonth() + 1
    const day = date.getDate()
    const hour = date.getHours()
    const minute = date.getMinutes()
    const second = date.getSeconds()

    return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
    n = n.toString()
    return n[1] ? n : '0' + n
}

//正则判断
function Regular(str, reg) {
    if (reg.test(str))
        return true;
    return false;
}

//是否为中文
function IsChinese(str) {
    var reg = /^[\u0391-\uFFE5]+$/;
    return Regular(str, reg);
}


function wxAjax(url, param, success) {
    wx.request({
        url: getRequestUrl + url,
        data: param,
        method: 'POST',
        header: {'content-type': 'application/json'},
        success: function (res) {
            success(res);
        }
        // success: success
    })
}

module.exports = {
    formatTime: formatTime,
    getRequestUrl: getRequestUrl,
    IsChinese: IsChinese,
    wxAjax: wxAjax,
    uri: {
        login: "/wx/login/login",
        findUserInfo: "/wx/login/findUserInfo"
    }
};
