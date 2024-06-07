async function loadComentarios(articuloId, currentUserEmail) {

    const response = await fetch("/articulo/" + articuloId + "/comentarios");
    const comentarios = await response.json();
    console.log(comentarios);

    let commentsSection = document.getElementById('comment1');
    commentsSection.innerHTML = '';
    comentarios.forEach(comment => {
        let commentElement1 = document.createElement('div')
        commentElement1.classList.add("comment-section")
        let commentElement = document.createElement('div');
        commentElement.classList.add("comment");
        commentElement.textContent = comment.username + ' : ' + comment.contenido;

        commentElement1.appendChild(commentElement);
                
        // If the current user is the owner of the comment, add delete button
            if (comment.email === currentUserEmail) {
                let deleteForm = document.createElement('form');
                deleteForm.method = 'GET';
                deleteForm.action = `/comentario/delete/${comment.comentarioId}`;
                
                let deleteButton = document.createElement('button');
                deleteButton.type = 'submit';
                deleteButton.classList.add('btn-trash');

                deleteButton.innerHTML = '<i class="fa-solid fa-trash-can"></i>';
                    
                deleteForm.appendChild(deleteButton);
                commentElement1.appendChild(deleteForm);
            }
            commentsSection.appendChild(commentElement1);
        });
}

async function init(articuloId, currentUserEmail) {
    loadComentarios(articuloId, currentUserEmail);
    setInterval(() => loadComentarios(articuloId, currentUserEmail), 60000); // Actualiza cada 5 segundos
    
}
