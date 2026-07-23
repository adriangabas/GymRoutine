document.addEventListener('DOMContentLoaded', function () {
    // Navbar: añadir clase 'scrolled' al hacer scroll
    var navbar = document.getElementById('mainNavbar');
    if (navbar) {
        window.addEventListener('scroll', function () {
            if (window.scrollY > 20) {
                navbar.classList.add('scrolled');
            } else {
                navbar.classList.remove('scrolled');
            }
        });
    }

    // Fade-in al hacer scroll usando IntersectionObserver
    var fadeEls = document.querySelectorAll('.gr-fade-in');
    if ('IntersectionObserver' in window) {
        var observer = new IntersectionObserver(function (entries) {
            entries.forEach(function (entry) {
                if (entry.isIntersecting) {
                    entry.target.classList.add('visible');
                    observer.unobserve(entry.target);
                }
            });
        }, { threshold: 0.12 });

        fadeEls.forEach(function (el) {
            observer.observe(el);
        });
    } else {
        // Fallback: mostrar todo directamente
        fadeEls.forEach(function (el) {
            el.classList.add('visible');
        });
    }

    // Contador animado para las estadísticas numéricas
    var statValues = document.querySelectorAll('.gr-stat-value[data-target]');
    var statObserver = new IntersectionObserver(function (entries) {
        entries.forEach(function (entry) {
            if (entry.isIntersecting) {
                animateCounter(entry.target);
                statObserver.unobserve(entry.target);
            }
        });
    }, { threshold: 0.5 });

    statValues.forEach(function (el) {
        statObserver.observe(el);
    });

    function animateCounter(el) {
        var target = parseInt(el.getAttribute('data-target'), 10);
        var duration = 1500;
        var start = performance.now();

        function update(now) {
            var elapsed = now - start;
            var progress = Math.min(elapsed / duration, 1);
            // easeOutQuart
            var eased = 1 - Math.pow(1 - progress, 4);
            var current = Math.floor(eased * target);
            el.textContent = (target > 99 ? '+' : '') + current;
            if (progress < 1) {
                requestAnimationFrame(update);
            } else {
                el.textContent = (target > 99 ? '+' : '') + target;
            }
        }

        requestAnimationFrame(update);
    }

    // Cerrar el menú móvil al hacer clic en un enlace
    var navLinks = document.querySelectorAll('.navbar-nav .nav-link');
    var navCollapse = document.getElementById('navbarMenu');
    if (navCollapse) {
        navLinks.forEach(function (link) {
            link.addEventListener('click', function () {
                if (navCollapse.classList.contains('show')) {
                    var bsCollapse = bootstrap.Collapse.getInstance(navCollapse);
                    if (bsCollapse) {
                        bsCollapse.hide();
                    }
                }
            });
        });
    }
});
