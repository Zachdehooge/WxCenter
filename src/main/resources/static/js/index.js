(function(){
    var root = document.documentElement;
    var toggle = document.getElementById('themeToggle');
    function applyTheme(theme){
        root.setAttribute('data-theme', theme);
        toggle.textContent = theme === 'light' ? '☀️' : '🌙';
    }
    applyTheme('dark');
    toggle.addEventListener('click', function(){
        var next = root.getAttribute('data-theme') === 'light' ? 'dark' : 'light';
        applyTheme(next);
    });

    var saveBtn = document.getElementById('tokenSave');
    var status = document.getElementById('tokenStatus');
    var input = document.getElementById('tempestToken');
    saveBtn.addEventListener('click', function(){
        if(!input.value.trim()) return;
        status.style.display = 'block';
        setTimeout(function(){ status.style.display = 'none'; }, 3000);
    });
})();