

// function will reload the page only one time to allow the database time to update on the page
function reload() {
    if (!localStorage.getItem('hasExecuted')) {
        history.go();
        /* tell local storage that method has been executed.
        Once it is set to "true" the method will not run again
         */
        localStorage.setItem('hasExecuted', true);
    }
}
// call reload()
window.onload = reload();

