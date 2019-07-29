function article_load_content(ele) {

    $("#article_table tbody").empty();
    var articles= ele.extend.Articles.list;
    $.each(articles,function (index,item) {
        var titleT= $("<h3></h3>").append(item.title);
        var summaryT= $("<p></p>").append(item.summary);

        var articleAllT=$("<a style=\"float: left;\" href=\"#\">阅读全文>></a>").addClass("articleAll") .attr("articleId",item.articleId);

        var autherT=$(" <small style=\"float: right;\">作者：</small>").append(item.auther);

        var dateT=$(" <small style=\"float: right;\">&nbsp;&nbsp;&nbsp;</small>").append(item.createTime);

        var art_dateT=$("<div class=\"look\" style=\"width: 100%\"></div>").append(articleAllT).append(dateT).append(autherT);
        var dividerT=$("<div class=\"divider\" style=\"margin-top: 30px\"><HR align=center color=#987cb9 SIZE=1></div>");
        $("<div class=\"article\"></div>").append(titleT).append(summaryT).append(art_dateT).append(dividerT).appendTo("#article_table tbody");
    });
}

function article_load_nav(result) {

    $('#page_nav').empty();
    var url=$("<ul></ul>").addClass("pagination");

    var firstpage=$("<li></li>").append($("<a></a>").attr("href","#").append("首页"));

    var prepage=$("<li></li>").append($("<a></a>").append("&laquo;"));

    if(result.extend.Articles.isFirstPage){
        firstpage.addClass("disabled");
        url.append(firstpage);
    }else{
        url.append(firstpage);
        url.append(prepage);
    }


    $.each(result.extend.Articles.navigatepageNums,function (index,item) {
        var page=$("<li></li>").append($("<a></a>").append(item));
        if(result.extend.Articles.pageNum==item){
            page.addClass("active");
        }
        page.click(function () {
            article_load(item);
        });
        url.append(page);
    })
    var nextpage=$("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastpage=$("<li></li>").append($("<a></a>").attr("href","#").append("末页"));
    if(result.extend.Articles.isLastPage){
        lastpage.addClass("disabled");

    }else{
        url.append(nextpage);
    }
    url.append(lastpage);
    var nav=$("<nav></nav>").append(url);
    nav.appendTo("#page_nav");

    firstpage.click(function () {
        article_load(result.extend.Articles.firstPage);
    });
    prepage.click(function () {
        article_load(result.extend.Articles.prePage);
    });

    nextpage.click(function () {
        article_load(result.extend.Articles.nextPage);
    });
    lastpage.click(function () {
        article_load(result.extend.Articles.lastPage);
    });
}

function category_load(ele) {
    $("#category_nev").empty();
    $.each(ele,function (index,item) {

        var categoryT= $("<li class=\"list-group-item category\"></li>").append($("<a></a>").append(item.categoryType)).attr("categoryId",item.categoryId);
        categoryT.appendTo("#category_nev");
    });
}


