document.addEventListener("DOMContentLoaded", async () => {
    const user = JSON.parse(localStorage.getItem("user"));
    if (!user || !user.student?.studentId) {
        alert("You must be a student to view this page.");
        window.location.href = "index.html";
        return;
    }

    const studentId = user.student.studentId;

    try {
        const res = await fetch(`http://localhost:8080/student/${studentId}`);
        if (!res.ok) throw new Error("Failed to load profile");
        const student = await res.json();

        document.getElementById("studentId").textContent = student.studentId;
        document.getElementById("firstName").textContent = student.firstName || "";
        document.getElementById("lastName").textContent = student.lastName || "";
        document.getElementById("qualification").textContent = student.qualification || "";
        document.getElementById("university").textContent = student.university || "";


        document.getElementById("newFirstName").value = student.firstName || "";
        document.getElementById("newLastName").value = student.lastName || "";
        document.getElementById("newQualification").value = student.qualification || "";
        document.getElementById("newUniversity").value = student.university || "";

    } catch (err) {
        console.error(err);
        alert("Error loading profile.");
    }
});

function enableEdit() {
    document.getElementById("profileView").style.display = "none";
    document.getElementById("profileEdit").style.display = "block";
}

async function submitEdit() {
    const user = JSON.parse(localStorage.getItem("user"));
    const studentId = user.student.studentId;

    const updatedStudent = {
        firstName: document.getElementById("newFirstName").value,
        lastName: document.getElementById("newLastName").value,
        qualification: document.getElementById("newQualification").value,
        university: document.getElementById("newUniversity").value,

    };

    try {
        const res = await fetch(`http://localhost:8080/student/${studentId}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(updatedStudent)
        });

        if (!res.ok) throw new Error("Update failed");

        // Go back to profile view
        window.location.href = "profile.html";
    } catch (err) {
        console.error(err);
        alert("Failed to update profile.");
    }
}
