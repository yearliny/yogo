$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});
function hold(el) {
    $.ajax({
        type: "POST",
        url: "/yg-admin/commentStatus",
        data: {
            id: el.parentNode.parentNode.dataset.id,
            status: "HOLD"
        }
    })
}