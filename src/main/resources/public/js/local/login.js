$(function(){
	//登陆的url
	var loginUrl = '/local/login';
	
	$('#submit').click(function(){
		var username = $('#username').val();
		var password = $('#password').val();
		$.ajax({
			url:loginUrl,
			type:"post",
			data:{
				username:username,
				password:password
			},
			success:function(data){
				if(data.success){
					window.location.href="/frontend/shoplist";
				}else{
					$.toast("用户名或密码错误！");
				}
			}
		});
	});
});