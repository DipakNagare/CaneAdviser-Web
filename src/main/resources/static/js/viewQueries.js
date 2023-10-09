   // Function to show SweetAlert modal for Assign action
   function showAssignModal() {
    Swal.fire({
        title: 'Assign',
        input: 'select',
        inputOptions: {
            'Expert1': 'Expert1',
            'Expert2': 'Expert2',
            'Expert3': 'Expert3'
        },
        inputPlaceholder: 'Select an expert',
        showCancelButton: true,
        confirmButtonText: 'Assign',
        cancelButtonText: 'Cancel',
        showLoaderOnConfirm: true,
        preConfirm: (expertName) => {
            // Handle the assignment logic here
            // You can use AJAX or any other method to assign the query
            return new Promise((resolve) => {
                setTimeout(() => {
                    // Simulate a successful assignment
                    resolve();
                }, 2000);
            });
        },
        allowOutsideClick: () => !Swal.isLoading()
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire('Assigned!', 'The query has been assigned.', 'success');
        }
    });
}

    // Function to show SweetAlert modal for Answer action
    function showAnswerModal() {
        Swal.fire({
            title: 'Answer',
            input: 'text',
            inputPlaceholder: 'Enter the answer',
            showCancelButton: true,
            confirmButtonText: 'Save',
            cancelButtonText: 'Cancel',
            showLoaderOnConfirm: true,
            preConfirm: (answer) => {
                // Handle the answer submission logic here
                // You can use AJAX or any other method to save the answer
                return new Promise((resolve) => {
                    setTimeout(() => {
                        // Simulate a successful answer submission
                        resolve();
                    }, 2000);
                });
            },
            allowOutsideClick: () => !Swal.isLoading()
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire('Saved!', 'The answer has been saved.', 'success');
            }
        });
    }
    // Function to show SweetAlert modal for Delete action
    function showDeleteModal() {
        Swal.fire({
            title: 'Delete',
            text: 'Are you sure you want to delete these Records?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: '#3085d6',
            confirmButtonText: 'Delete',
            cancelButtonText: 'Cancel',
            showLoaderOnConfirm: true,
            preConfirm: () => {
                // Handle the delete logic here
                // You can use AJAX or any other method to delete the records
                return new Promise((resolve) => {
                    setTimeout(() => {
                        // Simulate a successful deletion
                        resolve();
                    }, 2000);
                });
            },
            allowOutsideClick: () => !Swal.isLoading()
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire('Deleted!', 'The records have been deleted.', 'success');
            }
        });
    }

     // Function to open the logo modal
function openLogoModal(src, alt) {
    var logoModal = document.getElementById('logoModal');
    var logoImg = document.getElementById('logoImg');
    var logoCaption = document.getElementById('logoCaption');
    
    logoModal.style.display = "block";
    logoImg.src = src;
    logoImg.alt = alt;
    logoCaption.innerHTML = alt;
  }
  
  // Get the <span> element that closes the logo modal
  var logoModalClose = document.getElementsByClassName("close")[1];
  
  // When the user clicks on <span> (x), close the logo modal
  logoModalClose.onclick = function() {
    var logoModal = document.getElementById('logoModal');
    logoModal.style.display = "none";
  }
  