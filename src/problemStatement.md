# Document Management Application Design

## Overview
A document manager is an application that can be used to create, share, and manage text documents. It provides an interface to easily save and retrieve documents.

## Requirements
- **Create new document:** Users can create new text documents.
- **Update the document:** Users can update existing documents, creating new versions.
- **Delete a document:** Users can delete documents.
- **Get latest active version:** Users can retrieve the latest version of a document.
- **Revert to a previous version:** Users can revert a document to a previous version.
- **Access management:** Only the author of a document can edit, revert, or delete it. Anyone can view the document.

## Mandatory Functionality
### User Management
- `createUser()`: Create a new user in the system.
- `loginUser()`: Login a user using an ID and password.

### Document
- `createDocument()`: Add a fresh document.
- `updateDocument()`: Update an existing document, creating a new version.
- `deleteDocument()`: Delete a document.
- `getLatestDocumentVersion()`: Get the latest active version of a document.
- `revertToVersion()`: Revert a document to a previous version.

### Access Management
- Only the author can make changes to the document.
- Anyone can view the document.

## Additional Functionalities (if time permits)
- Sharing edit access between multiple users.
- Private access - only people with access to the document can view it.
- State Management - Document can be in DRAFT, PUBLISHED states.

## Constraints
- **Execution time limit:** 3 seconds (Java)
- **Memory limit:** 1 GB
