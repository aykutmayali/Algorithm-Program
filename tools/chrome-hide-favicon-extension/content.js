const TRANSPARENT_FAVICON =
  'data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"></svg>';

function ensureFaviconTag() {
  let tag = document.querySelector('link[rel~="icon"]');
  if (!tag) {
    tag = document.createElement('link');
    tag.rel = 'icon';
    document.head?.appendChild(tag);
  }

  tag.href = TRANSPARENT_FAVICON;

  document
    .querySelectorAll('link[rel~="icon"], link[rel="apple-touch-icon"]')
    .forEach((node) => {
      if (node !== tag) {
        node.remove();
      }
    });
}

function start() {
  ensureFaviconTag();

  const observer = new MutationObserver(() => {
    ensureFaviconTag();
  });

  if (document.head) {
    observer.observe(document.head, {
      childList: true,
      subtree: true,
      attributes: true,
      attributeFilter: ['href', 'rel']
    });
  } else {
    const headObserver = new MutationObserver(() => {
      if (document.head) {
        ensureFaviconTag();
        observer.observe(document.head, {
          childList: true,
          subtree: true,
          attributes: true,
          attributeFilter: ['href', 'rel']
        });
        headObserver.disconnect();
      }
    });

    headObserver.observe(document.documentElement, {
      childList: true,
      subtree: true
    });
  }
}

start();
