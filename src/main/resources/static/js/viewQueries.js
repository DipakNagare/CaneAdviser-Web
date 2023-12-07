function showAnswerModal(queId, query, image1, image2, image3) {
    // Create a modal with SweetAlert
    Swal.fire({
        title: 'Answer Query',
        html: `
    <div style="display: flex; flex-direction: column;">
    <div style="display: flex; margin-bottom: 10px;">
    <label for="showingQueryId" style="margin-right: 8px; min-width: 100px;"><strong>Query:</strong></label>
    <p id="showingQueryId">${queId}</p>
</div>
        <div style="display: flex; margin-bottom: 10px;">
            <label for="showingQuery" style="margin-right: 8px; min-width: 100px;"><strong>Query:</strong></label>
            <p id="showingQuery">${query}</p>
        </div>
        
        <div style="display: flex; margin-bottom: 10px;">
            <label for="showingImage" style="margin-right: 8px; min-width: 100px;"><strong>Images:</strong></label>
            <img src="data:image/jpeg;base64,${image1}" alt="NA" style="max-width:100px; max-height:100px; margin-right: 8px;" onclick="showFullImage('data:image/jpeg;base64,${image1}', 'Image 1')">
            <img src="data:image/jpeg;base64,${image2}" alt="NA" style="max-width:100px; max-height:100px; margin-right: 8px;" onclick="showFullImage('data:image/jpeg;base64,${image2}', 'Image 2')">
            <img src="data:image/jpeg;base64,${image3}" alt="NA" style="max-width:100px; max-height:100px;" onclick="showFullImage('data:image/jpeg;base64,${image3}', 'Image 3')">
        </div>

        <div style="display: flex; margin-bottom: 10px;">
            <label for="answerInput" style="margin-right: 8px; min-width: 100px;"><strong>Answer:</strong></label>
            <input type="text" id="answerInput" class="swal2-input">
        </div>
    </div>
`,
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Submit Answer'
    }).then((result) => {
        if (result.isConfirmed) {
            // Retrieve the answer from the input field
            const answer = document.getElementById('answerInput').value;
            fetch(`http://localhost:8081/queries/submit-answer?queId=${parseInt(queId)}&answer=${answer}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    queId: parseInt(queId),
                    answer: answer,
                }),
            })
                .then(response => {
                    if (response.ok) {
                        return response.text(); // Assuming the server responds with plain text
                    } else {
                        throw new Error(`Server responded with ${response.status} ${response.statusText}`);
                    }
                })
                .then(data => {
                    // Handle the server response, show success message
                    Swal.fire('Submitted!', `Response: ${data}`, 'success');
                })
                .catch(error => {
                    // Handle errors, show error message
                    console.error('Error submitting answer:', error);
                    Swal.fire('Error!', `Failed to submit the answer. Error: ${error.message}`, 'error');
                });
        }
    });
}






// Function to show SweetAlert modal for Assign actio
function showAssignModal(quesId) {
    fetch('http://localhost:8081/user-masters')
        .then(response => response.json())
        .then(data => {
            const expertUserIds = data.map(user => user.userId);

            // Use expertUserIds to dynamically create options for the dropdown
            const options = expertUserIds.map(userId => `<option value="${userId}">${userId}</option>`).join('');

            Swal.fire({
                title: 'Assign',
                html: `<label for="expertSelect">Expert:</label>
                       <select id="expertSelect" class="swal2-select">
                           ${options}
                       </select>
                       <br>
                       <label for="quesId">Ques_Id:</label>
                       <input type="text" id="quesId" class="swal2-input" value="${quesId}" readonly>`,

                showCancelButton: true,
                confirmButtonText: 'Assign',
                cancelButtonText: 'Cancel',
                showLoaderOnConfirm: true,
                preConfirm: async () => {
                    const selectedUserId = document.getElementById('expertSelect').value;
                    const selectedQuesId = document.getElementById('quesId').value;

                    // Make the POST request immediately
                    try {
                        const response = await fetch('http://localhost:8081/user-masters/assign-query', {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json',
                            },
                            body: JSON.stringify({
                                userId: selectedUserId,
                                questionId: parseInt(selectedQuesId),
                            }),
                        });
                        if (!response.ok) {
                            const errorMessage = await response.text();
                            throw new Error(`Error assigning query. Status: ${response.status}. Message: ${errorMessage}`);
                        }

                        return await response.text();
                    } catch (error) {
                        return error.message; // Return the error message
                    }
                },
                allowOutsideClick: () => !Swal.isLoading(),
            }).then((result) => {
                // The then block will be executed immediately after making the POST request
                // Handle success or failure here
                if (result && result !== 'Error') {
                    Swal.fire('Assigned!', 'The query has been assigned.', 'success');
                } else {
                    Swal.fire('Error!', `Failed to assign the query: ${result}`, 'error');
                }
            });
        })
        .catch(error => {
            console.error('Error fetching expert user IDs:', error);
        });
}
// Function to show SweetAlert modal for Delete action
function showDeleteModal(queId) {
    Swal.fire({
        title: 'Delete',
        text: 'Are you sure you want to delete these Query?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Delete',
        cancelButtonText: 'Cancel',
        showLoaderOnConfirm: true,
    }).then((result) => {
        if (result.isConfirmed) {
            fetch(`http://localhost:8081/queries/delete-query/${queId}`, {
                method: 'DELETE'
            })
                .then(response => {
                    if (response.ok) {
                        return response.text();
                    } else {
                        throw new Error(`Server responded with ${response.status} ${response.statusText}`);
                    }
                })
                .then(data => {

                    removeTableRow(queId);

                    Swal.fire('Deleted!', `Response: ${data}`, 'success');
                    // You can add additional logic here, such as updating the UI
                })
                .catch(error => {
                    console.error('Error deleting query:', error);
                    Swal.fire('Error!', `Failed to delete the query. Error: ${error.message}`, 'error');
                });
        }
    });
}

// use to update table after performing delete operation

function removeTableRow(queId) {

    var table = document.getElementById('queriesTable'); 
    
    // Find the row with the queId and remove it
    for (var i = 0; i < table.rows.length; i++) {
        var row = table.rows[i];
        var idCell = row.cells[0]; // Assuming the first cell contains the queId
        if (idCell.innerHTML === queId.toString()) {
            table.deleteRow(i);
            break;
        }
    }
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
logoModalClose.onclick = function () {
    var logoModal = document.getElementById('logoModal');
    logoModal.style.display = "none";
}
