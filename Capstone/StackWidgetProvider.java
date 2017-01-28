package  com.example.android.capstone;

import android.appwidget.AppWidgetProvider;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.TaskStackBuilder;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.android.capstone.model.Hit;
import com.example.android.capstone.ui.PicDetail;

import static com.example.android.capstone.ui.PicDetail.EXTRA_PIC;

public class StackWidgetProvider extends AppWidgetProvider {
    public static final String TOAST_ACTION = "com.example.android.stackwidget.TOAST_ACTION";
    public static final String EXTRA_ITEM = "com.example.android.stackwidget.EXTRA_ITEM";
    public static final String Extra_Hit="com.example.android.stackwidget.EXTRA_HIT";
    public Hit hit;

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
        if (intent.getAction().equals(TOAST_ACTION)) {
            int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            int viewIndex = intent.getIntExtra(EXTRA_ITEM, 0);
           // Toast.makeText(context, "Touched view " + viewIndex, Toast.LENGTH_SHORT).show();
            hit=intent.getParcelableExtra(EXTRA_PIC);
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

            Intent toastIntent = new Intent(context, PicDetail.class);
            toastIntent.setAction(StackWidgetProvider.TOAST_ACTION);
            toastIntent.putExtra(EXTRA_PIC, hit);
            toastIntent.putExtra(PicDetail.origin,"collection");
            context.startActivity(toastIntent);
           intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
           // PendingIntent toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent,PendingIntent.FLAG_UPDATE_CURRENT);

         PendingIntent pendingIntent = TaskStackBuilder.create(context).addNextIntentWithParentStack(toastIntent).getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
          rv.setPendingIntentTemplate(R.id.stack_view,pendingIntent);

            //rv.setPendingIntentTemplate(R.id.stack_view, toastPendingIntent);

          //  PendingIntent startActivityPendingIntent = PendingIntent.getActivity(context, 0, toastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
           // rv.setPendingIntentTemplate(R.id.stack_view, startActivityPendingIntent);


            appWidgetManager.updateAppWidget(appWidgetIds[i], rv);

            toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            PendingIntent toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            rv.setPendingIntentTemplate(R.id.stack_view, toastPendingIntent);

            appWidgetManager.updateAppWidget(appWidgetIds[i], rv);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
}