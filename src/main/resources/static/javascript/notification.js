// Function to close notification by ID
function closeNotificationById(id) {
    const notification = document.getElementById(id);
    if (notification) {
        notification.classList.add('hiding');
        setTimeout(() => {
            if (notification && notification.parentNode) {
                notification.remove();
            }
        }, 300);
    }
}

// Make function globally available
window.closeNotification = closeNotificationById;

// Function to close notification element
function closeNotificationElement(notification) {
    if (notification) {
        notification.classList.add('hiding');
        setTimeout(() => {
            if (notification && notification.parentNode) {
                notification.remove();
            }
        }, 300);
    }
}

// Initialize notifications when DOM is ready
function initNotifications() {
    // Use event delegation for close buttons (works even if elements are added dynamically)
    document.addEventListener('click', function(e) {
        if (e.target && e.target.classList.contains('notification-close')) {
            const notification = e.target.closest('.notification');
            closeNotificationElement(notification);
        }
    });

    // Set up close button handlers for existing buttons
    const closeButtons = document.querySelectorAll('.notification-close');
    closeButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            e.stopPropagation();
            const notification = this.closest('.notification');
            closeNotificationElement(notification);
        });
    });

    // Automatic close after 5 seconds
    const notifications = document.querySelectorAll('.notification');
    notifications.forEach(notification => {
        setTimeout(() => {
            if (notification && notification.parentNode) {
                closeNotificationElement(notification);
            }
        }, 5000);
    });
}

// Run initialization when DOM is ready
if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', initNotifications);
} else {
    // DOM is already ready
    initNotifications();
}