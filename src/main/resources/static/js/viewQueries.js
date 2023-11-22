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
      title: 'Question related to Queries',
      html: `
      <div id="imageContainer" style="display: flex; gap: 10px;">
      <img class="thumbnail" src="images/sugarcan1.jpg" alt="Image 1" width="60" height="60" onclick="showFullImage('images/sugarcan1.jpg')">
      <i class="fa-solid fa-plus" style="font-size: 20px;"></i>
      <img class="thumbnail" src="images/sugarcan1.jpg" alt="Image 2" width="60" height="60" onclick="showFullImage('images/sugarcan1.jpg')">
      <i class="fa-solid fa-plus" style="font-size: 20px;"></i>
      <img class "thumbnail" src="images/sugarcan1.jpg" alt="Image 3" width="60" height="60" onclick="showFullImage('images/sugarcan1.jpg')">
    </div>
    <input type="text" id="swal-input1" class="swal2-input" placeholder="Enter your answer"> `,
      showCancelButton: true,
      confirmButtonText: 'Submit',
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
      allowOutsideClick: () => !Swal.isLoading(),
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire('Saved!', 'The answer has been saved.', 'success');
      }
    });
  }
  
  function showFullImage(imageUrl) {
    Swal.fire({
      title: 'Full Image',
      html: `<img src="${imageUrl}" alt="Full Image">`,
      showCloseButton: true,
    });
  }
y  

 // Function to show SweetAlert modal for Answer action
function showAnswerModal() {
  Swal.fire({
    title: 'Question related to Queries',
    html: `
      <div id="imageContainer">
        <img class="thumbnail" src="images/sugarcan1.jpg" alt="Image 1" width="60" height="60" onclick="showFullImage('images/sugarcan1.jpg')">
        <img class="thumbnail" src="images/sugarcan1.jpg" alt="Image 1" width="60" height="60" onclick="showFullImage('images/sugarcan1.jpg')">
        <img class="thumbnail" src="images/sugarcan1.jpg" alt="Image 1" width="60" height="60" onclick="showFullImage('images/sugarcan1.jpg')">
      </div>
      <input type="text" id="swal-input1" class="swal2-input" placeholder="Enter your answer">
    `,
    showCancelButton: true,
    confirmButtonText: 'Submit',
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
    allowOutsideClick: () => !Swal.isLoading(),
  }).then((result) => {
    if (result.isConfirmed) {
      Swal.fire('Saved!', 'The answer has been saved.', 'success');
    }
  });
}

function showFullImage(imageUrl) {
  Swal.fire({
    title: 'Full Image',
    html: `<img src="${imageUrl}" alt="Full Image">`,
    showCloseButton: true,
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
  