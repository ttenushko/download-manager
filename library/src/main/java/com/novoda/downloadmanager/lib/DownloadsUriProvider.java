package com.novoda.downloadmanager.lib;

import android.net.Uri;

class DownloadsUriProvider {

    private final Uri publiclyAccessibleDownloadsUri;
    private final Uri downloadsByBatchUri;
    private final Uri allDownloadsUri;
    private final Uri batchesUri;
    private final Uri contentUri;

    public static DownloadsUriProvider getInstance() {
        return LazyInitialisationHelper.INSTANCE;
    }

    private static class LazyInitialisationHelper {
        private static final DownloadsUriProvider INSTANCE = newInstance();
    }

    private static DownloadsUriProvider newInstance() {
        String authority = "content://" + DownloadProvider.AUTHORITY;

        Uri publiclyAccessibleDownloadsUri = Uri.parse(authority + "/" + DownloadsDestination.PUBLICLY_ACCESSIBLE_DOWNLOADS_URI_SEGMENT);
        Uri downloadsByBatchUri = Uri.parse(authority + "/downloads_by_batch");
        Uri allDownloadsUri = Uri.parse(authority + "/all_downloads");
        Uri batchesUri = Uri.parse(authority + "/batches");
        Uri contentUri = Uri.parse(authority + "/my_downloads");

        return new DownloadsUriProvider(
                publiclyAccessibleDownloadsUri,
                downloadsByBatchUri,
                allDownloadsUri,
                batchesUri,
                contentUri
        );
    }

    DownloadsUriProvider(
            Uri publiclyAccessibleDownloadsUri,
            Uri downloadsByBatchUri,
            Uri allDownloadsUri,
            Uri batchesUri,
            Uri contentUri) {
        this.publiclyAccessibleDownloadsUri = publiclyAccessibleDownloadsUri;
        this.downloadsByBatchUri = downloadsByBatchUri;
        this.allDownloadsUri = allDownloadsUri;
        this.batchesUri = batchesUri;
        this.contentUri = contentUri;
    }

    /**
     * The content URI for accessing publicly accessible downloads (i.e., it requires no
     * permissions to access this downloaded file)
     */
    public Uri getPubliclyAccessibleDownloadsUri() {
        return publiclyAccessibleDownloadsUri;
    }

    /**
     * The content:// URI to access downloads and their batch data.
     */
    public Uri getDownloadsByBatchUri() {
        return downloadsByBatchUri;
    }

    /**
     * The content URI for accessing all downloads across all UIDs (requires the
     * ACCESS_ALL_DOWNLOADS permission).
     */
    public Uri getAllDownloadsUri() {
        return allDownloadsUri;
    }

    /**
     * The content:// URI to access downloads owned by the caller's UID.
     */
    public Uri getBatchesUri() {
        return batchesUri;
    }

    /**
     * The content:// URI to access downloads owned by the caller's UID.
     */
    public Uri getContentUri() {
        return contentUri;
    }
}