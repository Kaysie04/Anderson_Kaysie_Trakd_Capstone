
// function will reload the page only one time to allow the database time to update on the page
function reloadOnceOnLocationReplace() {
    if (!document.referrer) {
        window.location.reload();
    }
}
reloadOnceOnLocationReplace();

