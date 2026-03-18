document.addEventListener('DOMContentLoaded', function() {
    const fileInput = document.getElementById('chatFile');
    const filePreview = document.getElementById('filePreview');
    const fileList = document.getElementById('fileList');
    const clearFilesBtn = document.getElementById('clearFiles');

    if (fileInput && filePreview && fileList && clearFilesBtn) {
        fileInput.addEventListener('change', function(e) {
            const files = Array.from(e.target.files);
            
            if (files.length > 0) {
                filePreview.style.display = 'block';
                fileList.innerHTML = '';
                
                files.forEach((file, index) => {
                    const fileItem = document.createElement('div');
                    fileItem.className = 'file-item';
                    fileItem.innerHTML = `
                        <span class="file-name" title="${file.name} (${formatFileSize(file.size)})">
                            📎 ${file.name}
                        </span>
                        <span class="file-size">${formatFileSize(file.size)}</span>
                    `;
                    fileList.appendChild(fileItem);
                });
            } else {
                filePreview.style.display = 'none';
            }
        });

        clearFilesBtn.addEventListener('click', function() {
            fileInput.value = '';
            filePreview.style.display = 'none';
            fileList.innerHTML = '';
        });
    }

    function formatFileSize(bytes) {
        if (bytes === 0) return '0 Bytes';
        const k = 1024;
        const sizes = ['Bytes', 'KB', 'MB', 'GB'];
        const i = Math.floor(Math.log(bytes) / Math.log(k));
        return parseFloat((bytes / Math.pow(k, i)).toFixed(2)) + ' ' + sizes[i];
    }
});
