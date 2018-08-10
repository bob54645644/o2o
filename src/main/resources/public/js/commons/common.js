function changeVerifyCode(img){
	img.src="/kaptcha"+"?num="+Math.floor(Math.random()*100);
}

//从当前的url中查找 name 对应的值
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return decodeURIComponent(r[2]);
	}
	return '';
}