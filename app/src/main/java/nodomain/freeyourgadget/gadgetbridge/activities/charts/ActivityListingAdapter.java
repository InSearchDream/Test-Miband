package nodomain.freeyourgadget.gadgetbridge.activities.charts;

import android.content.Context;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import nodomain.freeyourgadget.gadgetbridge.adapter.AbstractActivityListingAdapter;
import nodomain.freeyourgadget.gadgetbridge.model.ActivityKind;
import nodomain.freeyourgadget.gadgetbridge.util.DateTimeUtils;

public class ActivityListingAdapter extends AbstractActivityListingAdapter<StepAnalysis.StepSession> {
    public ActivityListingAdapter(Context context) {
        super(context);
    }

    @Override
    protected String getTimeFrom(StepAnalysis.StepSession item) {
        Date time = item.getStepStart();
        return DateTimeUtils.formatTime(time.getHours(), time.getMinutes());
    }

    @Override
    protected String getTimeTo(StepAnalysis.StepSession item) {
        Date time = item.getStepEnd();
        return DateTimeUtils.formatTime(time.getHours(), time.getMinutes());
    }

    @Override
    protected String getActivityName(StepAnalysis.StepSession item) {
        return ActivityKind.asString(item.getActivityKind(), getContext());
    }

    @Override
    protected String getStepLabel(StepAnalysis.StepSession item) {
        return String.valueOf(item.getSteps());
    }

    @Override
    protected String getDistanceLabel(StepAnalysis.StepSession item) {
        DecimalFormat df = new DecimalFormat("###m");
        //DecimalFormatSymbols symbols = df.getDecimalFormatSymbols();
        //symbols.setGroupingSeparator(' ');
        return df.format(item.getDistance());
    }

    @Override
    protected String getHrLabel(StepAnalysis.StepSession item) {
        return String.valueOf(item.getHeartRateAverage());
    }

    @Override
    protected String getIntensityLabel(StepAnalysis.StepSession item) {
        DecimalFormat df = new DecimalFormat("###.#");
        return df.format(item.getIntensity());
    }

    @Override
    protected String getDurationLabel(StepAnalysis.StepSession item) {
        long duration = item.getStepEnd().getTime() - item.getStepStart().getTime();
        return DateTimeUtils.formatDurationHoursMinutes(duration, TimeUnit.MILLISECONDS);
    }

    @Override
    public Boolean hasHR(StepAnalysis.StepSession item) {
        if (item.getHeartRateAverage() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected int getIcon(StepAnalysis.StepSession item) {
        int activityKind = item.getActivityKind();
        return ActivityKind.getIconId(activityKind);
    }


}
