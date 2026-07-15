const token = localStorage.getItem("token");

// Load Profile
async function loadProfile() {

    try {

        const response = await fetch("/api/profile", {

            headers: {
                Authorization: "Bearer " + token
            }

        });

        if (!response.ok) {

            alert("Failed to load profile");
            return;

        }

        const user = await response.json();

        document.getElementById("name").value = user.name;
        document.getElementById("email").value = user.email;

    } catch (err) {

        console.error(err);

    }

}

// Update Profile
document.getElementById("profileForm").addEventListener("submit", async function (e) {

    e.preventDefault();

    const profile = {

        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value

    };

    const response = await fetch("/api/profile", {

        method: "PUT",

        headers: {

            "Content-Type": "application/json",
            "Authorization": "Bearer " + token

        },

        body: JSON.stringify(profile)

    });

    if (response.ok) {

        alert("Profile Updated Successfully!");

        document.getElementById("password").value = "";

        loadProfile();

    } else {

        alert("Failed to Update Profile");

    }

});

loadProfile();