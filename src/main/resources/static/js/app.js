document.addEventListener("DOMContentLoaded", function () {
    initDeleteModal();
    initAutoHideAlerts();
    initActiveNav();
    initToastFromFlash();
});

/* ===== Modal xóa dùng chung ===== */
function initDeleteModal() {
    const deleteLinks = document.querySelectorAll("[data-delete-url]");
    const modal = document.getElementById("deleteModal");
    const confirmBtn = document.getElementById("confirmDeleteBtn");
    const cancelBtns = document.querySelectorAll("[data-close-modal]");

    if (!modal || !confirmBtn || deleteLinks.length === 0) return;

    let currentUrl = null;

    deleteLinks.forEach(link => {
        link.addEventListener("click", function (e) {
            e.preventDefault();
            currentUrl = this.getAttribute("data-delete-url");
            modal.classList.add("show");
        });
    });

    confirmBtn.addEventListener("click", function () {
        if (currentUrl) {
            window.location.href = currentUrl;
        }
    });

    cancelBtns.forEach(btn => {
        btn.addEventListener("click", function () {
            modal.classList.remove("show");
            currentUrl = null;
        });
    });

    modal.addEventListener("click", function (e) {
        if (e.target === modal) {
            modal.classList.remove("show");
            currentUrl = null;
        }
    });
}

/* ===== Alert tự ẩn ===== */
function initAutoHideAlerts() {
    const alerts = document.querySelectorAll(".alert");
    if (!alerts.length) return;

    setTimeout(() => {
        alerts.forEach(alert => {
            alert.style.transition = "all 0.3s ease";
            alert.style.opacity = "0";
            alert.style.transform = "translateY(-6px)";
            setTimeout(() => alert.remove(), 300);
        });
    }, 3000);
}

/* ===== Active menu ===== */
function initActiveNav() {
    const currentPath = window.location.pathname;
    document.querySelectorAll(".nav-link").forEach(link => {
        const href = link.getAttribute("href");
        if (href && currentPath.startsWith(href) && href !== "/") {
            link.classList.add("active");
        }
    });
}

/* ===== Toast ===== */
function showToast(title, message, type = "success") {
    let container = document.querySelector(".toast-container");

    if (!container) {
        container = document.createElement("div");
        container.className = "toast-container";
        document.body.appendChild(container);
    }

    const toast = document.createElement("div");
    toast.className = `toast ${type}`;
    toast.innerHTML = `
        <div class="toast-title">${title}</div>
        <div class="toast-message">${message}</div>
    `;

    container.appendChild(toast);

    setTimeout(() => {
        toast.style.transition = "all 0.3s ease";
        toast.style.opacity = "0";
        toast.style.transform = "translateX(20px)";
        setTimeout(() => toast.remove(), 300);
    }, 3000);
}

/* ===== Đọc flash message từ HTML ===== */
function initToastFromFlash() {
    const toastElement = document.getElementById("toast-data");
    if (!toastElement) return;

    const title = toastElement.dataset.title;
    const message = toastElement.dataset.message;
    const type = toastElement.dataset.type || "success";

    if (message) {
        showToast(title || "Thông báo", message, type);
    }
}