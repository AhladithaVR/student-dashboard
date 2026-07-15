const token = localStorage.getItem("token");

async function loadReports() {

    const response = await fetch("/api/dashboard", {
        headers: {
            Authorization: "Bearer " + token
        }
    });

    const data = await response.json();

    document.getElementById("students").innerText = data.totalStudents;
    document.getElementById("tasks").innerText = data.totalTasks;
    document.getElementById("attendance").innerText = data.totalAttendance;

    // BAR CHART
    const barCtx = document.getElementById("barChart").getContext("2d");

    new Chart(barCtx, {

        type: "bar",

        data: {

            labels: ["Students", "Tasks", "Attendance"],

            datasets: [{

                label: "Count",

                data: [
                    data.totalStudents,
                    data.totalTasks,
                    data.totalAttendance
                ],

                backgroundColor: [
                    "#1976d2",
                    "#43a047",
                    "#ff9800"
                ]

            }]

        },

        options: {

            responsive: true,

            maintainAspectRatio: false

        }

    });

    // PIE CHART
    const pieCtx = document.getElementById("pieChart").getContext("2d");

    new Chart(pieCtx, {

        type: "pie",

        data: {

            labels: ["Present", "Absent"],

            datasets: [{

                data: [
                    data.presentCount,
                    data.absentCount
                ],

                backgroundColor: [
                    "#4caf50",
                    "#f44336"
                ]

            }]

        },

        options: {

            responsive: true,

            maintainAspectRatio: false

        }

    });

}

loadReports();