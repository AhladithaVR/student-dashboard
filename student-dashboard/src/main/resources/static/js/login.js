console.log("NEW LOGIN JS RUNNING");
document.getElementById("loginForm").addEventListener("submit", async function (e) {

    e.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const error = document.getElementById("errorMessage");

    error.innerText = "";

    try {

        const response = await fetch("/api/auth/login", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                email: email,
                password: password
            })

        });


        if (!response.ok) {

            error.innerText = "Invalid Email or Password";
            return;

        }


        const data = await response.json();


        // Save JWT token
        localStorage.setItem("token", data.token);


        // Open dashboard page
        console.log("Redirecting to dashboard");
window.location.href = "/dashboard";

    }

    catch (err) {

        console.error(err);
        error.innerText = "Server Error";

    }

});