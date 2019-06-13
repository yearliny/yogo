function unApprove(el) {
    $.ajax({
        type: "GET",
        url: "/yg-admin/commentStatus",
        data: {
            id: el.parentNode.parentNode.dataset.id,
            status: "HOLD"
        }
    })
}