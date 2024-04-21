Overview:
A document manager is an application that can be used to create, share and manage text documents. It provides an interface to easily save and retrieve documents.

Design a document management application which can be used to create, update, view, and delete documents. It should maintain all the versions of the updates and also have the functionality to revert the document to a previous version.

For the scope of this problem you can assume a document to be a page with only text data.
History of changes to a document to be maintained.
A document can be reverted to a previous version.
Only the user who created the document can edit, revert or delete the document. Anyone can view the document.
Mandatory Functionality:

User Management: Registration and login
Document:
Create new document
Update the document
Delete a document
Get latest active version of a document
Revert to a previous version
Access management:
Only the author can make changes to the document
Anyone can view
Functions to help you think:
You can use this section to help you think, but not limited to implementing just these

createUser(): This can be used to create a new user in the system.
loginUser(): This can be used to login a user, using some id and password.
getDocument(): Gets a document to view
createDocument(): Add a fresh document.
updateDocument(): Update an existing document, creating a new version.
revertToVersion(): Revert a document to a previous version.
Additional Functionalities to implement if you have time:

Sharing edit access between multiple users
Private access - only the people with access to the document can view it.
State Management - Document can be in DRAFT, PUBLISHED states.
[execution time limit] 3 seconds (java)

[memory limit] 1 GB