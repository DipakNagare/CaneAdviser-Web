// india map





// Function to fetch data from the backend and update the pie chart
function fetchPieChartDataAndRenderChart() {
    fetch('http://localhost:8081/analytics/technologyWiseCount')
        .then((response) => response.json())
        .then((data) => {
            // Extract the labels and data for the pie chart
            const pieLabels = data.map((item) => item[0]);
            const pieData = data.map((item) => item[1]);

            // Update the pie chart data
            pieChart.data.labels = pieLabels;
            pieChart.data.datasets[0].data = pieData;
            pieChart.update();
        })
        .catch((error) => {
            console.error('Error fetching data:', error);
        });
}

// Create pie chart
const pieChartData = {
    labels: ['Fertilizer Schedule', 'Production Technologies', 'Protection Technologies', 'Saccharum Species', 'Sugarcane Varieties'],
    datasets: [{
        data: [0, 0, 0, 0, 0], // Initial data
        backgroundColor: ['#FF5733', '#33FF57', '#5733FF', '#33FFAA', '#33AAFF'],
    }],
};

const pieCtx = document.getElementById('pieChart').getContext('2d');
            const pieChart = new Chart(pieCtx, {
                type: 'pie',
                data: pieChartData,
                options: {
                    plugins: {
                        legend: {
                            display: true,
                            position: 'right',
                            labels: {
                                boxWidth: 10,
                                padding: 20,
                            },
                        },
                    },
                },
            });
// Fetch and render pie chart data
fetchPieChartDataAndRenderChart();


// Function to fetch data from the backend and update the bar chart
function fetchMonthlyCountsAndRenderChart() {
    fetch('http://localhost:8081/queries/queriesYearMonthWise')
        .then((response) => response.json())
        .then((data) => {
            // Extract the months and counts for the bar chart
            console.log(data);
            const barMonths = Array.from({ length: 12 }, (_, i) => i + 1); 
            const barCounts = Array(12).fill(0); 

            data.forEach((item) => {
                const monthNumber = item[0];
                const count = item[1];
                barCounts[monthNumber - 1] = count;
            });
            console.log("barmonth",barMonths); // Log barMonths
            console.log("barCount",barCounts); // Log barCounts
        

            // Update the bar chart data
            barChart.data.labels = barMonths;
            barChart.data.datasets[0].data = barCounts;
            barChart.update();
        })
        .catch((error) => {
            console.error('Error fetching data:', error);
        });
}

// Create bar chart
const barChartData = {
    // labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
    datasets: [{
        label: 'Monthly Counts',
        data: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12], // Initial data with integer values
        backgroundColor: '#33AAFF', // Change the color to your preference
    }],
};

const barCtx = document.getElementById('barChart').getContext('2d');
const barChart = new Chart(barCtx, {
    type: 'bar',
    data: barChartData,
    options: {
        scales: {
            y: {
                beginAtZero: true,
                max: 10, // Set the maximum value as needed
                stepSize: 1, // Set the step size to 1
                callback: function (value, index, values) {
                    return Math.floor(value); // Display integer values
                },
            },
        },
    },
});

// Fetch and render bar chart data
fetchMonthlyCountsAndRenderChart();
