package  com.example.android.capstone.ui.widget;

import android.appwidget.AppWidgetProvider;
import com.example.android.capstone.R;
import com.example.android.capstone.model.CanvasDownloadTable;
import com.example.android.capstone.model.Hit;
import com.example.android.capstone.ui.PicDetail;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import java.util.List;

public class StackWidgetProvider extends AppWidgetProvider {
    public static final String WIDGET_ACTION = "com.example.android.stackwidget.WIDGET_ACTION";
    public static final String EXTRA_ITEM = "com.example.android.stackwidget.EXTRA_ITEM";

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager mgr = AppWidgetManager.getInstance(context);
        if (intent.getAction().equals(WIDGET_ACTION)) {
            int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            int viewIndex = intent.getIntExtra(EXTRA_ITEM, 0);

            List<Hit> mWidgetItems = CanvasDownloadTable.getRows(context.getContentResolver().query(CanvasDownloadTable.CONTENT_URI, null, null, null, null), true);
            Intent intent_detail = new Intent(context, PicDetail.class);
            intent_detail.putExtra(PicDetail.EXTRA_PIC, mWidgetItems.get(viewIndex));
            intent_detail.putExtra(PicDetail.origin,"widget");
            intent_detail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent_detail);
        }
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int i = 0; i < appWidgetIds.length; ++i) {

            Intent intent = new Intent(context, StackWidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            rv.setRemoteAdapter(appWidgetIds[i], R.id.stack_view, intent);

            rv.setEmptyView(R.id.stack_view, R.id.empty_view);

            Intent widgetIntent = new Intent(context, StackWidgetProvider.class);
            widgetIntent.setAction(StackWidgetProvider.WIDGET_ACTION);
            widgetIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            PendingIntent widgetPendingIntent = PendingIntent.getBroadcast(context, 0, widgetIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            rv.setPendingIntentTemplate(R.id.stack_view, widgetPendingIntent);

            appWidgetManager.updateAppWidget(appWidgetIds[i], rv);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
}