// Add your chart initialization code here


const pieChartData = {
    labels: ['State A', 'State B', 'State C'],
    datasets: [{
        data: [30, 40, 30],
        backgroundColor: ['#FF5733', '#33FF57', '#5733FF'],
    }],
};

const barChartData = {
    labels: ['Category 1', 'Category 2', 'Category 3', 'Category 4'],
    datasets: [{
        label: 'Bar Chart Data',
        data: [50, 60, 70, 80],
        backgroundColor: ['#FF5733', '#33FF57', '#5733FF', '#33FFAA'],
    }],
};

// Create pie chart
const pieCtx = document.getElementById('pieChart').getContext('2d');
const pieChart = new Chart(pieCtx, {
    type: 'pie',
    data: pieChartData,
});

// Create bar chart
const barCtx = document.getElementById('barChart').getContext('2d');
const barChart = new Chart(barCtx, {
    type: 'bar',
    data: barChartData,
});