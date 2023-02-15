/**
 * Created by 13 on 2017/2/22.
 */
// Tags Input
// .tagsInput是tagsinput文件夹下的js文件中的一个函数
$('#tags').tagsInput({
    width: '100%',
    height: '35px',
    defaultText: '请输入文章标签'
});

$('.toggle').toggles({
    on: true,
    text: {
        on: '是',
        off: '否'
    }
});

$(".select2").select2({
    width: '100%'
});

var tale = new $.tale();

/**
 * 添加公告
 */
function addNotice(){
    //通过调用的按钮找到此id的标签，获取里面input标签，name属性为title的
    var title = $('#articleForm input[name=title]').val();
    var content = $('#detail').val();
    if (title == '') {
        tale.alertWarn('请输入公告标题');
        return;
    }
    if (content == '') {
        tale.alertWarn('请输入公告内容');
        return;
    }
    // 设置 第一个匹配id=content-editor的属性 value=content。
    // $('#content-editor').val(content);
    // $("#articleForm #status").val(status);
    // $("#articleForm #categories").val($('#multiple-sel').val());
    var params = $("#articleForm").serialize();
    var url = '/admin/notice/add';
    tale.post({
        url:url,
        data:params,
        success: function (result) {
            if (result && result.success) {
                tale.alertOk({
                    text:'添加成功',
                    then: function () {
                        setTimeout(function () {
                            window.location.href = '/admin/notice';
                        }, 500);
                    }
                });
            } else {
                tale.alertError(result.msg || '添加失败');
            }
        }
    });
};


/**
 * 添加事件
 */
function addEvent(){
    //通过调用的按钮找到此id的标签，获取里面input标签，name属性为title的
    var title = $('#articleForm input[name=title]').val();
    var start_time = $('#articleForm input[name=start_time]').val();
    var end_time = $('#articleForm input[name=end_time]').val();
    var content = $('#detail').val();
    if (title == '') {
        tale.alertWarn('请输入标题');
        return;
    }
    if (content == '') {
        tale.alertWarn('请输入内容');
        return;
    }
    // 设置 第一个匹配id=content-editor的属性 value=content。
    // $('#content-editor').val(content);
    // $("#articleForm #status").val(status);
    // $("#articleForm #categories").val($('#multiple-sel').val());
    var params = $("#articleForm").serialize();
    var url = '/admin/event/add';
    tale.post({
        url:url,
        data:params,
        success: function (result) {
            if (result && result.success) {
                tale.alertOk({
                    text:'添加成功',
                    then: function () {
                        setTimeout(function () {
                            window.location.href = '/admin/event';
                        }, 500);
                    }
                });
            } else {
                tale.alertError(result.msg || '添加失败');
            }
        }
    });
};

/**
 * 保存文章
 * @param status
 */
function subArticle(status) {
    var title = $('#articleForm input[name=title]').val();
    var content = $('#text').val();
    if (title == '') {
        tale.alertWarn('请输入文章标题');
        return;
    }
    if (content == '') {
        tale.alertWarn('请输入文章内容');
        return;
    }
    $('#content-editor').val(content);
    $("#articleForm #status").val(status);
    $("#articleForm #categories").val($('#multiple-sel').val());
    var params = $("#articleForm").serialize();
    var url = $('#articleForm #cid').val() != '' ? '/admin/article/modify' : '/admin/article/publish';
    tale.post({
        url:url,
        data:params,
        success: function (result) {
            if (result && result.success) {
                tale.alertOk({
                    text:'文章保存成功',
                    then: function () {
                        setTimeout(function () {
                            window.location.href = '/admin/article';
                        }, 500);
                    }
                });
            } else {
                tale.alertError(result.msg || '保存文章失败');
            }
        }
    });
}

var textarea = $('#text'),
    toolbar = $('<div class="markdown-editor" id="md-button-bar" />').insertBefore(textarea.parent())
preview = $('<div id="md-preview" class="md-hidetab" />').insertAfter('.markdown-editor');

// markdown(textarea, toolbar, preview);


function allow_comment(obj) {
    var this_ = $(obj);
    var on = this_.find('.toggle-on.active').length;
    var off = this_.find('.toggle-off.active').length;
    if (on == 1) {
        $('#allow_comment').val(false);
    }
    if (off == 1) {
        $('#allow_comment').val(true);
    }
}

function allow_ping(obj) {
    var this_ = $(obj);
    var on = this_.find('.toggle-on.active').length;
    var off = this_.find('.toggle-off.active').length;
    if (on == 1) {
        $('#allow_ping').val(false);
    }
    if (off == 1) {
        $('#allow_ping').val(true);
    }
}


function allow_feed(obj) {
    var this_ = $(obj);
    var on = this_.find('.toggle-on.active').length;
    var off = this_.find('.toggle-off.active').length;
    if (on == 1) {
        $('#allow_feed').val(false);
    }
    if (off == 1) {
        $('#allow_feed').val(true);
    }
}

// $('div.allow-false').toggles({
//     off: true,
//     text: {
//         on: '开启',
//         off: '关闭'
//     }
// });

$('div.allow-false').toggles({
    off: true,
    text: {
        on: '是',
        off: '否'
    }
});

