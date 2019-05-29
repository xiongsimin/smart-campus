$(document).ready(function () {
    var height;//浏览器高度
    var head;//页面头部高度
    var main;//主体高度
    var bars_height;//bars元素高度
    var bars_ul_li_height;//li元素高度
    var font_size;//字体大小
    var msg_block_li_a_padding_top_bottom;//下拉菜单插件正常显示
    var left;
    var right;
    changeSize();


//删除对应的iframe(删除bars操作在元素中)
    $(document).on("click", "#bars ul li img", function () {
        var id = $(this).attr("bar-id");//获取标记该bar的id
        $("#ifrs iframe[bar-id='" + id + "']").remove();//移除对应iframe
        var pre_ifr = $("#ifrs iframe").eq(-1);//获取上一个iframe
        pre_ifr.css("display", "block");//显示上一个iframe
        $(this).parent().remove();//移除对应li
        $("#bars ul li[bar-id='" + pre_ifr.attr("bar-id") + "']").attr("class", "live");//根据上一个iframe的ifr-id获取上一个bar
    });


    /*
    **展开/折叠菜单
    */
    $("#left dl dt").on("click", function () {
        if ($(this).children(".menu_switch").attr("rotate") == "0") {//如果rotate当前状态为0，即关闭状态
            $(this).children(".menu_switch").animate({rotate: "90"}, 500);//将折叠图标旋转角度设为90
            $(this).children(".menu_switch").attr("rotate", "1");//将rotate置1
            $(this).parent().children("dd").slideDown(500);//将菜单下的子菜单显示出来
        } else if ($(this).children(".menu_switch").attr("rotate") == "1") {//如果rotate当前状态不为0，即展开状态
            $(this).children(".menu_switch").animate({rotate: "0"}, 500);//将折叠图标旋转角度设为0
            $(this).children(".menu_switch").attr("rotate", "0");//将rotate置0
            $(this).parent().children("dd").slideUp(500);//将菜单下的子菜单隐藏
        }

    });
});

function changeSize() { //浏览器窗口大小改变事件
    height = window.innerHeight;//浏览器高度
    head = height * 0.07;//页面头部高度
    main = height * 0.93;//主体高度
    left = $("#left").css("width").substr(0, $("#left").css("width").indexOf("p"));//获取左侧菜单栏宽度
    right = window.innerWidth - left;//计算出右侧宽度
    bars_height = main * 0.05;//bars元素高度
    bars_ul_li_height = main * 0.05;//li元素高度
    font_size = $("#head a").css("line-height");//字体大小
    font_size = font_size.substr(0, font_size.indexOf("p"));//去掉“px”
    msg_block_li_a_padding_top_bottom = (head - font_size) / 2;//下拉菜单插件正常显示

    if (window.innerHeight >= 300) { //浏览器高度大于300px时缩小比例
        $("#head").css("height", head);
        $("#head").css("line-height", head + "px");
        $("#left").css("height", main);
        $("#right").css("width", right);
        $("#bars").css("height", bars_height);
        $("#bars ul li").css("line-height", bars_ul_li_height + "px");
        $("#msg_block ul li a").css("padding-top", msg_block_li_a_padding_top_bottom + "px");
        $("#msg_block ul li a").css("padding-bottom", msg_block_li_a_padding_top_bottom + "px");
        $("#msg_block ul li a").css("color", "white");
        $("#msg_block ul li ul li a").css("color", "black");
        // $("#bars ul li").css("padding-top", main * 0.05*0.2+"px");
        $("#ifrs").css("height", main * 0.95);
    } else {//否则固定大小，防止挤压变形
        $("#head").css("height", "20px");
        $("#head").css("line-height", "20px");
        $("#left").css("height", "280px");
        $("#right").css("width", right);
        $("#bars").css("height", "15px");
        $("#bars ul li").css("line-height", "15px");
        $("#msg_block ul li a").css("padding-top", "0px");
        $("#msg_block ul li a").css("padding-bottom", "0px");
        $("#msg_block ul li a").css("color", "white");
        $("#msg_block ul li ul li a").css("color", "black");
        // $("#bars ul li").css("padding-top", main * 0.05*0.2+"px");
        $("#ifrs").css("height", "165px");
    }

}

function to_index() {
    $("#bars ul li").attr("class", "");//去掉所有li的“live”样式
    $("#bars ul li[bar-id='0']").attr("class", "live");//首页li加上“live”样式
    $("#ifrs iframe").css("display", "none");
    $("#ifrs iframe[bar-id='0']").css("display", "block");
}

//鼠标经过bars的li元素时更换显眼的图标
function chang_close_img(id) {
    $(id).attr("src", "/imgs/hover_close.png");
}

//鼠标离开bars的li元素时还原图标
function release_close_img(id) {
    $(id).attr("src", "/imgs/close.png");
}

//新建一个bars和iframe
function bars_new(bars_name, id, url) {
    //如果该栏已存在，则block，否则新建
    if ($("#bars ul li[bar-id='" + id + "']").length > 0) {
        //刷新该栏
        $("#bars ul li").attr("class", "");//去掉所有li的“live”样式
        $("#bars ul li[bar-id='" + id + "']").attr("class", "live");//该li加上“live”样式

        //如果当前活动页为其他，则隐藏其他页，并显示该页
        //$("#ifrs iframe[src='" + url + "']").attr("src",url);
        $("#ifrs iframe").css("display", "none");
        $("#ifrs iframe[bar-id='" + id + "']").css("display", "block");

    } else {
        //新建该栏
        $("#bars ul li").attr("class", "");//去掉所有li的“live”样式
        //新建li
        //url用来标记对应哪个iframe
        $("#bars ul").append("<li onclick='bars_new(\"" + bars_name + "\",\"" + id + "\",\"" + url + "\");' bar-id='" + id + "' class='live'> " + bars_name + "<img bar-id='" + id + "' onmouseleave='release_close_img(this);' onmouseover='chang_close_img(this);' src='/imgs/close.png' alt='' /></li>");
        $("#bars ul li").css("line-height", bars_ul_li_height + "px");
        //新建iframe
        $("#ifrs").append("<iframe src=" + url + " bar-id='" + id + "'></iframe>");
        $("#ifrs iframe").css("display", "none");
        $("#ifrs iframe[bar-id='" + id + "']").css("display", "block");
    }
}