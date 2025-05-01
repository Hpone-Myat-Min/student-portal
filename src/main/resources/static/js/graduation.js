document.addEventListener("DOMContentLoaded", async () => {
    const user = JSON.parse(localStorage.getItem("user"));
    if (!user || !user.student?.studentId) {
        alert("You must be a student to view this page.");
        window.location.href = "index.html";
        return;
    }

    const studentId = user.student.studentId;
    const container = document.getElementById("graduationStatus");

    try {
        const res = await fetch(`http://localhost:8080/student/graduation/${studentId}`);
        if (!res.ok) throw new Error("Failed to fetch graduation status");

        const eligibleToGrad = await res.json(); // expecting true or false

        if (!eligibleToGrad) {
            container.innerHTML = `<p style="color:red;">You have outstanding invoices. Please pay at the portal before graduating.</p>`;
        } else {
            container.innerHTML = `<p style="color:green;">Congratulations! You are eligible to graduate.</p>`;
        }

    } catch (err) {
        console.error(err);
        container.innerHTML = `<p style="color:red;">Error checking graduation status. Please try again later.</p>`;
    }
});

function signOut() {
    localStorage.removeItem("user");
    window.location.href = "index.html";
}

