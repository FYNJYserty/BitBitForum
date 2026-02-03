// Reset settings function
function resetSettings() {
    if (confirm('Are you sure you want to reset all settings to default?')) {
        // Reset fields to default values
        document.querySelectorAll('.toggle-input').forEach(toggle => {
            toggle.checked = false;
        });
        document.getElementById('theme-color').value = 'blue';
    }
}

// Initialize settings page when DOM is loaded
document.addEventListener('DOMContentLoaded', function() {
    // Add any additional initialization here if needed
    console.log('Settings page loaded');
});
