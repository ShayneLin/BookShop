function _change() {
	$("#vCode").attr("src",
			"/bookshop/verifyCodeAction?" + new Date().getTime());
}