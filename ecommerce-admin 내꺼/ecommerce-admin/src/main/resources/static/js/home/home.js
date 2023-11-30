const salesMetricUrl = "http://localhost:8081/api/sales-per-categories";
const productListUrl = "/product/list";
$(function () {
    $.ajax({
        dataType: "json",
        url: salesMetricUrl,
    }).done(function (data) {
        console.log("data.categories = " + data.categories);
        console.log("data.sales = " + data.sales);
        myPieChart.data.labels = data.categories;
        myPieChart.data.datasets[0].data = data.sales;
        myPieChart.update();
    });
});

